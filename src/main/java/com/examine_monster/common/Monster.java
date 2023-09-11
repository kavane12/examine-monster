package com.examine_monster.common;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/*
 * A class representing a monster.
 */
@Slf4j
@Getter
public class Monster
{
    // Basic details
    int id;
    String name;
    String wikiUrl;

    // Attributes
    int combatLevel;
    int attackSpeed;
    int maxHit;
    List<String> attackStyles;
    List<String> attributes;
    boolean aggressive;
    boolean poisonous;
    boolean venomous;
    boolean immunePoison;
    boolean immuneVenom;

    // Combat stats
    int hitpoints;
    int attackLevel;
    int strengthLevel;
    int defenseLevel;
    int magicLevel;
    int rangedLevel;

    // Aggressive stats
    int attackBonus;
    int strengthBonus;
    int magicAttack;
    int magicBonus;
    int rangedAttack;
    int rangedBonus;

    // Defensive stats
    int defenseStab;
    int defenseSlash;
    int defenseCrush;
    int defenseMagic;
    int defenseRanged;

    // Drops
    List<ItemDrop> drops;

    public Monster(JsonObject monsterJson, List<JsonObject> itemsJson)
    {
        log.info("Creating Monster");
        try (JsonReader reader = new JsonReader(new StringReader(monsterJson.toString())))
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
                    case "wiki_url":
                        this.wikiUrl = reader.nextString();
                        break;
                    case "combat_level":
                        this.combatLevel = reader.nextInt();
                        break;
                    case "attack_speed": // nullable
                        this.attackSpeed = reader.nextInt();
                        break;
                    case "max_hit": // nullable
                        this.maxHit = reader.nextInt();
                        break;
                    case "attack_type":
                        reader.beginArray();
                        this.attackStyles = new ArrayList<String>();
                        while (reader.hasNext())
                            this.attackStyles.add(reader.nextString());
                        reader.endArray();
                        break;
                    case "attributes":
                        reader.beginArray();
                        this.attributes = new ArrayList<String>();
                        while (reader.hasNext())
                            this.attributes.add(reader.nextString());
                        reader.endArray();
                        break;
                    case "aggressive":
                        this.aggressive = reader.nextBoolean();
                        break;
                    case "poisonous":
                        this.poisonous = reader.nextBoolean();
                        break;
                    case "venomous":
                        this.venomous = reader.nextBoolean();
                        break;
                    case "immune_poison":
                        this.immunePoison = reader.nextBoolean();
                        break;
                    case "immune_venom":
                        this.immuneVenom = reader.nextBoolean();
                        break;
                    case "hitpoints": // nullable
                        this.hitpoints = reader.nextInt();
                        break;
                    case "attack_level":
                        this.attackLevel = reader.nextInt();
                        break;
                    case "strength_level":
                        this.strengthLevel = reader.nextInt();
                        break;
                    case "defence_level":
                        this.defenseLevel = reader.nextInt();
                        break;
                    case "magic_level":
                        this.magicLevel = reader.nextInt();
                        break;
                    case "ranged_level":
                        this.rangedLevel = reader.nextInt();
                        break;
                    case "attack_bonus":
                        this.attackBonus = reader.nextInt();
                        break;
                    case "strength_bonus":
                        this.strengthBonus = reader.nextInt();
                        break;
                    case "attack_magic":
                        this.magicAttack = reader.nextInt();
                        break;
                    case "magic_bonus":
                        this.magicBonus = reader.nextInt();
                        break;
                    case "attack_ranged":
                        this.rangedAttack = reader.nextInt();
                        break;
                    case "ranged_bonus":
                        this.rangedBonus = reader.nextInt();
                        break;
                    case "defence_stab":
                        this.defenseStab = reader.nextInt();
                        break;
                    case "defence_slash":
                        this.defenseSlash = reader.nextInt();
                        break;
                    case "defence_crush":
                        this.defenseCrush = reader.nextInt();
                        break;
                    case "defence_magic":
                        this.defenseMagic = reader.nextInt();
                        break;
                    case "defence_ranged":
                        this.defenseRanged = reader.nextInt();
                        break;
                    case "drops":
                        this.drops = itemsJson.stream().map(itemJson -> new ItemDrop(itemJson)).toList();
                        reader.skipValue();
                        break;
                    default:
                        reader.skipValue();
                        break;
                }

            }
            reader.endObject();
            log.info("Finished creating Monster");
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
