package com.examine_monster.common;

import java.io.IOException;
import java.io.StringReader;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import lombok.extern.slf4j.Slf4j;

/*
 * A single item drop of a monster;
 */
@Slf4j
public class Item
{
    // Item info
    int id;
    String name;
    String wikiUrl;

    int highAlchValue;
    String icon; // base64

    // Drop info
    String quantity;
    float rarity;

    public Item(JsonObject itemJson)
    {
        try (JsonReader reader = new JsonReader(new StringReader(itemJson.toString())))
        {
            reader.beginObject();
            while (reader.hasNext())
            {
                switch (reader.nextName())
                {
                    case "id":
                        this.id = reader.nextInt();
                        break;
                    case "name":
                        this.name = reader.nextString();
                        break;
                    case "wiki_url": // nullable
                        if (reader.peek() == JsonToken.NULL)
                        {
                            reader.skipValue();
                            this.wikiUrl = "";
                            break;
                        }
                        this.wikiUrl = reader.nextString();
                        break;
                    case "highalch": // nullable
                        if (reader.peek() == JsonToken.NULL)
                        {
                            reader.skipValue();
                            this.highAlchValue = 0;
                            break;
                        }
                        this.highAlchValue = reader.nextInt();
                        break;
                    case "quantity": // nullable
                        if (reader.peek() == JsonToken.NULL)
                        {
                            this.quantity = "1";
                            reader.skipValue();
                            break;
                        }
                        this.quantity = reader.nextString();
                        break;
                    case "rarity":
                        this.rarity = (float) reader.nextDouble();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
        }
        catch (IOException e)
        {
            log.error("", e);
        }
        catch (IllegalStateException e)
        {
            log.error("", e);
        }
        catch (NumberFormatException e)
        {
            log.error("", e);
        }
    }
}