package com.examine_monster.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.examine_monster.common.Monster;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DataClient
{
    /**
     * Looks up a monster by its id and returns it.
     *
     * @param monsterId
     *            the id of the monster
     * @return a {@link CompletableFuture} of {@link Monster}.
     */
    public static CompletableFuture<Monster> lookupMonster(int id)
    {
        return OsrsReboxedClient.requestGetMonsterJson(id)
                .thenCompose(monsterJson -> getItemsJson(monsterJson)
                        .thenApply(itemsJson -> new Monster(monsterJson, itemsJson)));
    }

    /**
     * Makes multiple calls to fetch items and returns a single CompletableFuture of
     * the final list of item jsons.
     */
    private static CompletableFuture<List<JsonObject>> getItemsJson(JsonObject monsterJson)
    {
        List<CompletableFuture<JsonObject>> itemsJson$ = new ArrayList<>();

        for (JsonElement itemJsonEle : monsterJson.get("drops").getAsJsonArray())
        {
            JsonObject itemJsonObj = itemJsonEle.getAsJsonObject();
            int itemId = itemJsonObj.get("id").getAsInt();

            // Get some properties from the Osrs Wiki.
            itemsJson$.add(OsrsWikiClient.requestGetItemJson(itemId).thenApply(resultItemJson ->
            {
                // Then add properties found on monsterJson to it as well.
                resultItemJson.addProperty("id", itemId);
                resultItemJson.addProperty("wiki_url", OsrsWikiClient.ITEM_URL + itemId);

                String name = itemJsonObj.get("name").getAsString();
                resultItemJson.addProperty("name", name);

                String quantity = itemJsonObj.get("quantity").getAsString();
                resultItemJson.addProperty("quantity", quantity);

                float rarity = itemJsonObj.get("rarity").getAsFloat();
                resultItemJson.addProperty("rarity", rarity);

                return resultItemJson;
            }));
        }

        return CompletableFuture.allOf(itemsJson$.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> itemsJson$.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }
}
