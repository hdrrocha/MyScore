package com.example.myscore.data.model

class CreditReportInfo {
    var score: Double? = null
    var scoreBand: Double? = null
    var clientRef: String? = null
    var status: String? = null
    var maxScoreValue: Double? = null
    var minScoreValue: Double? = null
    var monthsSinceLastDefaulted: Double? = null
    var hasEverDefaulted: Boolean? = null
    var monthsSinceLastDelinquent: Double? = null
    var hasEverBeenDelinquent: Boolean? = null
    var percentageCreditUsed: Double? = null
    var percentageCreditUsedDirectionFlag: Double? = null
    var changedScore: Double? = null
    var currentShortTermDebt: Double? = null
    var currentShortTermNonPromotionalDebt: Double? = null
    var currentShortTermCreditLimit: Double? = null
    var currentShortTermCreditUtilisation: Double? = null
    var changeInShortTermDebt: Double? = null
    var currentLongTermDebt: Double? = null
    var currentLongTermNonPromotionalDebt: Double? = null
    var currentLongTermCreditLimit: String? = null
    var currentLongTermCreditUtilisation: String? = null
    var changeInLongTermDebt: Double? = null
    var numPositiveScoreFactors: Double? = null
    var numNegativeScoreFactors: Double? = null
    var equifaxScoreBand: Double? = null
    var equifaxScoreBandDescription: String? = null
    var daysUntilNextReport: Double? = null
}

