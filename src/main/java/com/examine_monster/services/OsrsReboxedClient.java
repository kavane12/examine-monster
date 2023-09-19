package com.examine_monster.services;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Contains static methods for accessing osrsreboxed-db Static JSON API.
 */
public class OsrsReboxedClient
{
    private static final String MONSTER_URL = "https://raw.githubusercontent.com/0xNeffarion/osrsreboxed-db/master/docs/monsters-json/";
    private static final String ITEM_URL = "https://raw.githubusercontent.com/0xNeffarion/osrsreboxed-db/master/docs/items-json/";

    public static final OkHttpClient client = new OkHttpClient();
    public static final Gson gson = new Gson();

    /**
     * Get request on osrsreboxed-db to fetch the specified monster by id.
     *
     * @param monsterId
     *            the id of the monster
     * @return a {@link CompletableFuture} of the monster {@code JSON}.
     */
    public static CompletableFuture<JsonObject> requestGetMonsterJson(int monsterId)
    {
        CompletableFuture<JsonObject> monsterJson$ = new CompletableFuture<>();

        Request request = new Request.Builder().url(MONSTER_URL + monsterId + ".json").build();
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
    public static CompletableFuture<JsonObject> requestGetItemJson(int itemId)
    {
        CompletableFuture<JsonObject> itemJson$ = new CompletableFuture<>();

        Request request = new Request.Builder().url(ITEM_URL + itemId + ".json").build();
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
