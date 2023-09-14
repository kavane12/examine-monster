package com.examine_monster.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.examine_monster.common.Monster;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Singleton;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

enum Endpoint
{
    MONSTER("monsters-json/"),
    ITEM("items-json/"),
    ITEM_ICON("items-icons/");

    public static final String OSRS_REBOXED_BASE_URL = "https://raw.githubusercontent.com/0xNeffarion/osrsreboxed-db/master/docs/";
    public final String url;

    private Endpoint(String url)
    {
        this.url = OSRS_REBOXED_BASE_URL + url;
    }
}

/**
 * Contains methods for accessing OSRS Reboxed's osrsreboxed-db Static JSON API.
 */
@Singleton
public class OsrsReboxedClient
{
    public static final OkHttpClient client = new OkHttpClient();
    public static final Gson gson = new Gson();

    /**
     * Looks up a monster by its id and returns it.
     *
     * @param monsterId
     *            the id of the monster
     * @return a {@link CompletableFuture} of {@link Monster}.
     */
    public static CompletableFuture<Monster> lookupMonster(int id)
    {
        return requestGetMonsterJson(id)
                .thenCompose(monsterJson -> getItemsJson(monsterJson)
                        .thenApply(itemsJson -> new Monster(monsterJson, itemsJson)));
    }

    private static CompletableFuture<List<JsonObject>> getItemsJson(JsonObject monsterJson)
    {
        List<CompletableFuture<JsonObject>> itemsJson$ = new ArrayList<>();

        for (JsonElement itemJsonEle : monsterJson.get("drops").getAsJsonArray())
        {
            JsonObject itemJsonObj = itemJsonEle.getAsJsonObject();
            int itemId = itemJsonObj.get("id").getAsInt();
            String quantity = itemJsonObj.get("quantity").getAsString();
            float rarity = itemJsonObj.get("rarity").getAsFloat();

            itemsJson$.add(requestGetItemsJson(itemId).thenApply(resultItemJson ->
            {
                // Add properties found on monsterJson but not itemJson for ItemDrop
                resultItemJson.addProperty("quantity", quantity);
                resultItemJson.addProperty("rarity", rarity);
                return resultItemJson;
            }));
        }

        return CompletableFuture.allOf(itemsJson$.toArray(new CompletableFuture<?>[0]))
                .thenApply(v -> itemsJson$.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));

    }

    /**
     * Get request on osrsreboxed-db to fetch the specified monster by id.
     *
     * @param monsterId
     *            the id of the monster
     * @return a {@link CompletableFuture} of the monster {@code JSON}.
     */
    private static CompletableFuture<JsonObject> requestGetMonsterJson(int monsterId)
    {
        CompletableFuture<JsonObject> monsterJson$ = new CompletableFuture<>();

        Request request = new Request.Builder().url(Endpoint.MONSTER.url + monsterId + ".json").build();
        client.newCall(request).enqueue(new Callback()
        {
            public void onResponse(Call call, Response response)
            {
                try (ResponseBody responseBody = response.body())
                {
                    if (response.isSuccessful() && responseBody != null)
                    {
                        JsonObject json = gson.fromJson(responseBody.string(), JsonObject.class);
                        monsterJson$.complete(json);
                    }
                    monsterJson$.complete(null);
                }
                catch (IOException e)
                {
                    monsterJson$.complete(null);
                }
                finally
                {
                    response.close();
                }
            }

            public void onFailure(Call call, IOException e)
            {
                monsterJson$.complete(null);
            }
        });

        return monsterJson$;
    }

    /**
     * Get request on osrsreboxed-db to fetch the specified item by id.
     *
     * @param itemId
     *            the id of the item
     * @return a {@link CompletableFuture} of the item {@code JSON}.
     */
    private static CompletableFuture<JsonObject> requestGetItemsJson(int itemId)
    {
        CompletableFuture<JsonObject> itemJson$ = new CompletableFuture<>();

        Request request = new Request.Builder().url(Endpoint.ITEM.url + itemId + ".json").build();
        client.newCall(request).enqueue(new Callback()
        {
            public void onResponse(Call call, Response response)
            {
                try (ResponseBody responseBody = response.body())
                {
                    if (response.isSuccessful() && responseBody != null)
                    {
                        JsonObject json = gson.fromJson(responseBody.string(), JsonObject.class);
                        itemJson$.complete(json);
                    }
                    itemJson$.complete(null);
                }
                catch (IOException e)
                {
                    itemJson$.complete(null);
                }
                finally
                {
                    response.close();
                }
            }

            public void onFailure(Call call, IOException e)
            {
                itemJson$.complete(null);
            }
        });

        return itemJson$;
    }
}
