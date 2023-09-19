package com.examine_monster.common;

import java.io.IOException;
import java.io.StringReader;

import javax.swing.ImageIcon;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/*
 * A single item drop of a monster;
 */
@Slf4j
@Getter
public class Item
{
    // Item info
    int id;
    String name;
    String wikiUrl;

    String geValue;
    String haValue;
    ImageIcon icon;

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
                            this.wikiUrl = null;
                            break;
                        }
                        this.wikiUrl = reader.nextString();
                        break;
                    case "ge_value": // nullable
                        if (reader.peek() == JsonToken.NULL)
                        {
                            reader.skipValue();
                            this.geValue = "Not sold";
                            break;
                        }
                        this.geValue = reader.nextString();
                        break;
                    case "ha_value": // nullable
                        if (reader.peek() == JsonToken.NULL)
                        {
                            reader.skipValue();
                            this.haValue = "N/A";
                            break;
                        }
                        this.haValue = reader.nextString();
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