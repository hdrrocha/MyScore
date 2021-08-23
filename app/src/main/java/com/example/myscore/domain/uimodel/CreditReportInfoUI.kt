package com.example.myscore.domain.uimodel


data class CreditReportInfoUI (
    val score: Double,
    val scoreBand: Double,
    val clientRef: String,
    val status: String,
    val maxScoreValue: Double,
    val minScoreValue: Double,
    val monthsSinceLastDefaulted: Double,
    val hasEverDefaulted: Boolean,
    val monthsSinceLastDelinquent: Double,
    val hasEverBeenDelinquent: Boolean,
    val percentageCreditUsed: Double,
    val percentageCreditUsedDirectionFlag: Double,
    val changedScore: Double,
    val currentShortTermDebt: Double,
    val currentShortTermNonPromotionalDebt: Double,
    val currentShortTermCreditLimit: Double,
    val currentShortTermCreditUtilisation: Double,
    val changeInShortTermDebt: Double,
    val currentLongTermDebt: Double,
    val currentLongTermNonPromotionalDebt: Double,
    val currentLongTermCreditLimit: String,
    val currentLongTermCreditUtilisation: String,
    val changeInLongTermDebt: Double,
    val numPositiveScoreFactors: Double,
    val numNegativeScoreFactors: Double,
    val equifaxScoreBand: Double,
    val equifaxScoreBandDescription: String,
    val daysUntilNextReport: Double
)