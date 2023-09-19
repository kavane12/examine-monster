package com.examine_monster.services;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Slf4j
public class OsrsWikiClient
{
    public static final String ITEM_URL = "https://oldschool.runescape.wiki/w/Special:Lookup?type=item&id=";

    public static final OkHttpClient client = new OkHttpClient();

    /**
     * Get request on the OSRS Wiki to fetch the specified item by id.
     *
     * @param itemId
     *            the id of the item
     * @return a {@link CompletableFuture} of the item {@code JSON}.
     */
    public static CompletableFuture<JsonObject> requestGetItemJson(int itemId)
    {
        CompletableFuture<JsonObject> itemJson$ = new CompletableFuture<>();

        Request request = new Request.Builder().url(ITEM_URL + itemId).build();
        client.newCall(request).enqueue(new Callback()
        {
            public void onResponse(Call call, Response response)
            {
                try (ResponseBody responseBody = response.body())
                {
                    if (response.isSuccessful() && responseBody != null)
                    {
                        itemJson$.complete(buildItemJsonFromHtml(responseBody.string()));
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

    /** Builds the Json object given the html page of an item. */
    private static JsonObject buildItemJsonFromHtml(String httpString)
    {
        Document document = Jsoup.parse(httpString);
        JsonObject itemJson = new JsonObject();

        Element geValueElement = document.selectFirst("span.infobox-quantity-replace");
        itemJson.addProperty("ge_value", geValueElement != null ? geValueElement.text() : null);

        Element haValueElement = document.selectFirst("table.infobox-item tr:contains(High alch)");
        // E.g: With the row being "High alch 999 coins", adds 999 as haValue property
        itemJson.addProperty("ha_value", haValueElement != null ? haValueElement.text().split(" ")[2] : null);

        return itemJson;
    }
}
