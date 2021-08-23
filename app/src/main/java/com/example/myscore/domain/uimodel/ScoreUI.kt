package com.example.myscore.domain.uimodel

data class ScoreUI (
    val accountIDVStatus: String,
    val creditReportInfo: CreditReportInfoUI,
    val dashboardStatus: String,
    val personaType: String,
    val coachingSummary: CoachingSummaryUI?,
    val augmentedCreditScore: String
)