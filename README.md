<div align="center">
    <a href="https://modrinth.com/project/datapack-utils">
        <img alt="modrinth" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/available/modrinth_vector.svg">
    </a>
    <a href="https://www.curseforge.com/minecraft/mc-mods/datapack-utils">
        <img alt="curseforge" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/available/curseforge_vector.svg">
    </a>
    <a href="https://github.com/Raik176/datapack-utils">
        <img alt="github" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/available/github_vector.svg">
    </a>
    <a href="https://discord.gg/FpEReTJbSA">
        <img alt="discord-plural" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/social/discord-plural_vector.svg">
    </a>
</div>

# Datapack Utils
Let's you do more things with datapacks.

As modern mods are technically also datapacks, you can use this to add fuels or compostables for your mod as well!

#### Examples:
  - [Custom Fuels](#custom-fuels)
  - [Custom Compostables](#custom-compostables)
  - [Inverse Tags](#inverse-tags-blacklist)

---

### Custom Fuels
This file allows you to specify custom fuels.

**Do not try to override vanilla/modded entries!**

Fields:
  * `item` the item to add the entry to.
  * `tag` the tag to add the entry to. If both this and the `item` field are present, only this one will be added.
  * `duration` fuel ticks for this entry. Coal has `1600`.

`data/<namespace>/utils/custom_fuels.json`
```json
[
  {
    "item": "minecraft:dirt",
    "duration": 2000
  }
]
```
Will add `minecraft:dirt` as a fuel, with a burn time of `2000` ticks.

---

### Custom Compostables
This file allows you to specify custom compostable items.

**Do not try to override vanilla/modded entries!**

Fields:
* `item` the item to add the entry to.
* `tag` the tag to add the entry to. If both this and the `item` field are present, only this one will be added.
* `chance` Chance for the compost to succeed.

`data/<namespace>/utils/custom_compostables.json`
```json
[
  {
    "item": "minecraft:dirt",
    "chance": 50
  }
]
```
Will add `minecraft:dirt` as a compostable, with a `50%` chance to succeed.

---

### Inverse Tags (Blacklist)
Inverse tags lets you remove entries from tags.

`data/<namespace>/utils/inverse-tags/<registry>/<tag location>.json`

Example:
`data/minecraft/utils/inverse-tags/block/spruce_logs.json`
```json
[
  "minecraft:stripped_spruce_log"
]
```
Will remove `minecraft:stripped_spruce_log` from the `minecraft:block/spruce_logs` tag.

# Credits
  * Icon: [Minecraft Datapack Utility](https://github.com/ChenCMD/MC-Datapack-Utility/blob/master/icon.png)