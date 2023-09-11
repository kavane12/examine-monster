package com.examine_monster.common;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import lombok.extern.slf4j.Slf4j;

/*
 * A class representing an item drop of a monster;
 */
@Slf4j
public class ItemDrop
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

    public ItemDrop(JsonObject itemJson)
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

    public String toString()
    {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(newLine + "ItemDrop {");
        result.append(newLine);

        // determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        // print field names paired with their values
        for (Field field : fields)
        {
            result.append("  ");
            try
            {
                result.append(field.getName());
                result.append(": ");
                // requires access to private field:
                result.append(field.get(this));
            }
            catch (IllegalAccessException ex)
            {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}