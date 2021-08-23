package com.example.myscore.data.model

class CreditReportInfo {
    var score = 0.0
    var scoreBand = 0.0
    var clientRef: String? = null
    var status: String? = null
    var maxScoreValue = 0.0
    var minScoreValue = 0.0
    var monthsSinceLastDefaulted = 0.0
    var hasEverDefaulted = false
    var monthsSinceLastDelinquent = 0.0
    var hasEverBeenDelinquent = false
    var percentageCreditUsed = 0.0
    var percentageCreditUsedDirectionFlag = 0.0
    var changedScore = 0.0
    var currentShortTermDebt = 0.0
    var currentShortTermNonPromotionalDebt = 0.0
    var currentShortTermCreditLimit = 0.0
    var currentShortTermCreditUtilisation = 0.0
    var changeInShortTermDebt = 0.0
    var currentLongTermDebt = 0.0
    var currentLongTermNonPromotionalDebt = 0.0
    var currentLongTermCreditLimit: String? = null
    var currentLongTermCreditUtilisation: String? = null
    var changeInLongTermDebt = 0.0
    var numPositiveScoreFactors = 0.0
    var numNegativeScoreFactors = 0.0
    var equifaxScoreBand = 0.0
    var equifaxScoreBandDescription: String? = null
    var daysUntilNextReport = 0.0
}
