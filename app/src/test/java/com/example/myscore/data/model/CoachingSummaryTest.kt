package com.example.myscore.data.model

import com.example.myscore.BaseUnitTest
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CoachingSummaryTest : BaseUnitTest() {
    @Test
    fun constructor() = runBlocking {
        val model = CoachingSummary()
        Truth.assertThat(model.activeTodo).isNull()
        Truth.assertThat(model.activeChat).isNull()
        Truth.assertThat(model.numberOfTodoItems).isNull()
        Truth.assertThat(model.numberOfCompletedTodoItems).isNull()
        Truth.assertThat(model.selected).isNull()
    }
}

