package app.waste2wealth.com.tags

import app.waste2wealth.com.R

data class Tag(
    val name: String,
    val image: String,
    val tips: List<String> = emptyList()
) {
    constructor() : this("", "", emptyList())

    fun mapWithoutTips() = TagWithoutTips(
        name = name,
        image = image
    )
}

data class TagWithoutTips(
    val name: String,
    val image: String,
) {
    constructor() : this("", "")

    fun mapWithTips() = Tag(
        name = name,
        image = image
    )
}

data class Groups(
    val name: String,
    val tags: List<Tag> = emptyList()
) {
    constructor() : this("", emptyList())

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            "${name.first()} ${tags.joinToString(" ") { it.name }}",
            "${tags.any()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}


val wasteGroups = listOf(
    Groups(
        name = "Plastic Waste",
        tags = listOf(
            Tag(
                name = "Plastic Bottles",
                tips = listOf(
                    "Recycle plastic bottles to reduce environmental impact.",
                    "Use reusable water bottles to minimize plastic bottle waste.",
                    "Dispose of plastic bottles properly in recycling bins.",
                    "Consider upcycling plastic bottles for DIY projects.",
                    "Avoid single-use plastic bottles whenever possible."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fplastic-bottle.png?alt=media&token=5ac75192-9ffd-4d2f-bab3-fc764d1ee609"
            ),
            Tag(
                name = "Plastic Bags",
                tips = listOf(
                    "Use reusable shopping bags to reduce plastic bag waste.",
                    "Recycle plastic bags at designated collection points.",
                    "Reduce plastic bag usage by opting for paper or cloth bags.",
                    "Avoid littering plastic bags to protect the environment.",
                    "Consider repurposing plastic bags for storage or crafts."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fplastic-bag.png?alt=media&token=5c473f87-13fe-4577-b029-68de9539b0f0"

            ),
            Tag(
                name = "Plastic Containers",
                tips = listOf(
                    "Recycle plastic containers with the recycling number on the bottom.",
                    "Avoid microwaving food in plastic containers to prevent chemical leaching.",
                    "Consider using glass or stainless steel containers for food storage.",
                    "Repurpose plastic containers for organizing small items.",
                    "Support businesses that offer plastic container recycling programs."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fplastic-container.png?alt=media&token=0bb3e02a-7e10-4d8e-8763-8401b8662329"
            ),
            Tag(
                name = "Plastic Wrappers",
                tips = listOf(
                    "Reduce plastic wrapper waste by choosing products with minimal packaging.",
                    "Recycle plastic wrappers at specialized collection points.",
                    "Opt for reusable beeswax wraps for food storage.",
                    "Avoid buying individually wrapped items.",
                    "Participate in plastic wrapper recycling initiatives in your community."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fplastic-wrapper.png?alt=media&token=5806649e-2312-4a2c-897a-a8e44218628c"


            ),
            Tag(
                name = "Plastic Toys",
                tips = listOf(
                    "Donate unwanted plastic toys to charity organizations.",
                    "Recycle broken plastic toys if possible.",
                    "Encourage children to take care of their plastic toys to extend their lifespan.",
                    "Avoid buying excessive plastic toys to reduce waste.",
                    "Look for eco-friendly and sustainable toy options made from biodegradable materials."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fplastic-toys.png?alt=media&token=257e2be8-e01b-4f85-afa7-79728f894228"
            )
        )
    ),
    Groups(
        name = "Paper Waste",
        tags = listOf(
            Tag(
                name = "Newspapers",
                tips = listOf(
                    "Recycle newspapers to save trees and energy.",
                    "Reuse old newspapers for wrapping or crafting.",
                    "Avoid throwing newspapers in the trash; recycle them instead.",
                    "Support digital news platforms to reduce paper waste.",
                    "Consider donating newspapers to animal shelters for bedding."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fpaper-newspaper.png?alt=media&token=5cd105ca-9f41-4e03-88d8-e1a5489289af"
            ),
            Tag(
                name = "Cardboard",
                tips = listOf(
                    "Flatten and recycle cardboard boxes to save space in landfills.",
                    "Use cardboard for DIY projects and crafts.",
                    "Reuse cardboard packaging for storage or shipping.",
                    "Recycle cardboard packaging from online shopping.",
                    "Support businesses that use eco-friendly cardboard packaging."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fpaper-cardboard.png?alt=media&token=afd40573-d1a8-4aab-8892-b5c523d66274"
            ),
            Tag(
                name = "Office Paper",
                tips = listOf(
                    "Recycle office paper to reduce paper waste in workplaces.",
                    "Use both sides of office paper for printing and note-taking.",
                    "Implement digital document management systems to reduce paper usage.",
                    "Encourage employees to recycle paper and use electronic communication.",
                    "Choose recycled office paper products to support sustainability."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fpaper-office.png?alt=media&token=a06b006c-2892-4ef7-a516-915b9a2c7674"
            ),
        )
    ),
    Groups(
        name = "Glass Waste",
        tags = listOf(
            Tag(
                name = "Glass Bottles",
                tips = listOf(
                    "Recycle glass bottles to conserve resources and energy.",
                    "Reuse glass bottles for homemade beverages or storage.",
                    "Dispose of broken glass bottles safely to prevent injuries.",
                    "Support bottle return programs to promote glass recycling.",
                    "Choose products with glass packaging to reduce waste."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fglass-bottle.png?alt=media&token=7d0544a8-e1c0-48e9-a9ec-1d6994d75788"
            ),
            Tag(
                name = "Broken Glass",
                tips = listOf(
                    "Handle broken glass with care to avoid injuries.",
                    "Safely dispose of broken glass in a puncture-proof container.",
                    "Label containers with broken glass to alert waste collectors.",
                    "Consider recycling broken glass at designated collection points.",
                    "Educate others about safe handling and disposal of broken glass."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fglass-broken.png?alt=media&token=05f019b4-9eb4-4f7a-ba6f-fb05c4f0c965"
            ),
            Tag(
                name = "Glassware",
                tips = listOf(
                    "Recycle glassware, such as drinking glasses and bowls.",
                    "Donate unwanted glassware to secondhand stores.",
                    "Repurpose glassware for creative DIY projects.",
                    "Avoid disposing of glassware in the trash.",
                    "Consider using durable glassware to reduce waste."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fglass-mirror.png?alt=media&token=9079e258-01e2-4dbb-a2a9-600e42d6cafc"
            )
        )
    ),
    Groups(
        name = "Metal Waste",
        tags = listOf(
            Tag(
                name = "Aluminum Cans",
                tips = listOf(
                    "Recycle aluminum cans to conserve energy and resources.",
                    "Crush cans to save space in recycling bins.",
                    "Avoid littering aluminum cans to protect the environment.",
                    "Consider collecting cans for recycling drives or charity.",
                    "Support metal recycling programs in your community."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fmetal-aluminium.png?alt=media&token=659e9d13-9461-4fd5-be4c-a9f27747eb56"
            ),
            Tag(
                name = "Steel Cans",
                tips = listOf(
                    "Recycle steel cans to reduce waste and save energy.",
                    "Rinse steel cans before recycling to prevent contamination.",
                    "Repurpose steel cans for organizing small items.",
                    "Support steel recycling initiatives in your area.",
                    "Choose products with minimal steel packaging to reduce waste."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fmetal-steel-can.png?alt=media&token=c80611a6-5e4d-42ab-b039-b7d114ff4569"
            ),
            Tag(
                name = "Scrap Metal",
                tips = listOf(
                    "Recycle scrap metal to prevent landfill waste.",
                    "Sort and separate different types of scrap metal for recycling.",
                    "Contact scrap metal yards for proper disposal and recycling.",
                    "Support businesses that buy and recycle scrap metal.",
                    "Avoid leaving scrap metal in public areas."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fmetal-scrap.png?alt=media&token=a331b92f-07b9-44c5-b346-9565b6aea587"
            ),

            // Add more tags and tips for Metal Waste...
        )
    ),
    Groups(
        name = "E-Waste",
        tags = listOf(
            Tag(
                name = "Smartphones",
                tips = listOf(
                    "Recycle old smartphones to recover valuable materials.",
                    "Donate functional smartphones to charitable organizations.",
                    "Erase personal data before disposing of smartphones.",
                    "Consider trading in old smartphones for discounts on new ones.",
                    "Support e-waste recycling programs in your region."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fe-mobile.png?alt=media&token=21aadf1a-c6e0-4a46-8baf-ea69c21c9989"
            ),
            Tag(
                name = "Computer Accessories",
                tips = listOf(
                    "Recycle computer accessories like keyboards, mice, and cables.",
                    "Donate functional computer accessories to charities or schools.",
                    "Properly dispose of non-functional computer accessories.",
                    "Support e-waste collection events in your area.",
                    "Avoid storing unused computer accessories indefinitely."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fe-computer.png?alt=media&token=54ae8d30-5c6e-4121-a384-fb461a6a67b7"
            ),
            Tag(
                name = "Printers",
                tips = listOf(
                    "Recycle old printers to prevent e-waste buildup.",
                    "Donate functional printers to organizations or schools.",
                    "Dispose of non-functional printers responsibly.",
                    "Support e-waste recycling initiatives in your region.",
                    "Minimize unnecessary printing to reduce printer waste."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fe-printer.png?alt=media&token=ff074e26-c665-460f-91aa-5870a649ba8e"
            )
            // Add more tags and tips for E-Waste...
        )
    ),
    Groups(
        name = "Organic Waste",
        tags = listOf(
            Tag(
                name = "Food Scraps",
                tips = listOf(
                    "Compost food scraps to create nutrient-rich soil.",
                    "Use a compost bin or pile for food waste decomposition.",
                    "Avoid composting meat and dairy products to prevent odors.",
                    "Learn about proper composting techniques for best results.",
                    "Support community composting programs."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2For-scraps.png?alt=media&token=6b68e921-20dd-40e5-b59e-1b2063aeb63e"
            ),
            Tag(
                name = "Garden Waste",
                tips = listOf(
                    "Use garden waste for mulching or composting.",
                    "Create a dedicated area for garden waste disposal.",
                    "Avoid disposing of garden waste in landfills.",
                    "Consider turning garden waste into organic fertilizer.",
                    "Support green waste collection services in your area."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2For-garden.png?alt=media&token=e6b0fe93-92ab-4ad8-b157-6fe90074f7ad"
            ),
            Tag(
                name = "Compost",
                tips = listOf(
                    "Compost fruit peels and skins to reduce waste.",
                    "Use citrus peels for natural cleaning solutions.",
                    "Repurpose fruit peels for homemade potpourri.",
                    "Avoid throwing fruit peels in the trash.",
                    "Support eco-friendly kitchen practices."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2For-compost.png?alt=media&token=5f9923df-6e05-4849-9556-bfd6c4dca5d6"
            ),
        )
    ),
    Groups(
        name = "Textile Waste",
        tags = listOf(
            Tag(
                name = "Clothing",
                tips = listOf(
                    "Donate gently used clothing to charities or shelters.",
                    "Host clothing swap events with friends and family.",
                    "Recycle worn-out clothing through textile recycling programs.",
                    "Consider upcycling old clothing into new fashion pieces.",
                    "Support sustainable fashion brands that promote recycling."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Ftextile-clothing.png?alt=media&token=d319dea9-419e-4c60-b309-b7853341445d"
            ),
            Tag(
                name = "Footwear",
                tips = listOf(
                    "Donate or recycle old footwear to reduce waste.",
                    "Repair and refurbish worn-out shoes to extend their lifespan.",
                    "Avoid discarding shoes in landfills.",
                    "Support footwear donation drives for those in need.",
                    "Choose sustainable footwear options made from recycled materials."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Ftextile-shoes.png?alt=media&token=dc1a8032-7e7c-47a7-9e53-b0c6fbb8d3f7"
            ),
            Tag(
                name = "Fabric Scraps",
                tips = listOf(
                    "Upcycle fabric scraps for sewing and crafting projects.",
                    "Donate excess fabric scraps to schools or community centers.",
                    "Recycle fabric scraps through textile recycling services.",
                    "Avoid adding fabric scraps to household waste.",
                    "Explore creative ways to repurpose fabric scraps in your home."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Ftextile-fabric.png?alt=media&token=88d56b44-a6b8-4df6-8b5b-cb6ce9a0864b"
            )
            // Add more tags and tips for Textile Waste...
        )
    ),
    Groups(
        name = "Medical Waste",
        tags = listOf(
            Tag(
                name = "Needles and Syringes",
                tips = listOf(
                    "Dispose of used needles and syringes in designated sharps containers.",
                    "Never recap needles or syringes, and avoid bending or breaking them.",
                    "Check local regulations for proper disposal guidelines.",
                    "Avoid throwing sharps in the regular trash.",
                    "Support safe needle disposal programs in your community."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fmedical-syringes.png?alt=media&token=844c77d6-2318-48af-aa83-3d401afad66c"
            ),
            Tag(
                name = "Expired Medications",
                tips = listOf(
                    "Return expired medications to pharmacies or designated collection sites.",
                    "Do not flush medications down the toilet or drain.",
                    "Follow disposal instructions on medication labels.",
                    "Keep medications out of reach of children and pets.",
                    "Support medication take-back programs for safe disposal."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fmedical-contaminated.png?alt=media&token=749ea1f8-54ac-4a09-b5ba-791943327891"
            ),
            Tag(
                name = "Medical Equipment",
                tips = listOf(
                    "Donate functional medical equipment to organizations in need.",
                    "Dispose of broken or non-functional medical equipment responsibly.",
                    "Follow manufacturer instructions for equipment disposal.",
                    "Support medical equipment recycling programs in your region.",
                    "Avoid contributing to medical waste through careful equipment usage."
                ),
                image = "https://firebasestorage.googleapis.com/v0/b/waste2wealth-v2.appspot.com/o/tags%2Fmedical-bandage.png?alt=media&token=5814645b-a2c6-4de1-9271-f6761e1b6708"
            )
            // Add more tags and tips for Medical Waste...
        )
    )
)

val allTags = getAllTagsFromGroups(wasteGroups)

fun getAllTagsFromGroups(wasteGroups: List<Groups>): List<Tag> {
    return wasteGroups.flatMap { it.tags }
}
