package com.example.myscore.injection

import android.content.Context
import com.example.myscore.data.api.ScoreApi
import com.example.myscore.data.repository.ScoreRepository
import com.example.myscore.data.repository.abs.ScoreRepositoryAbs
import com.example.myscore.domain.mapper.ScoreMapper
import com.example.myscore.domain.mapper.abs.ScoreMapperAbs
import com.example.myscore.domain.usecase.ScoreUseCase
import com.example.myscore.domain.usecase.abs.ScoreUseCaseAbs
import com.example.myscore.domain.viewmodel.ScoreViewModel
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import io.mockk.mockkClass
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit

class ModulesTest  : KoinTest {

    @Before
    fun setUp() {
        startKoin {
            androidContext(mockkClass(Context::class))
            modules(Modules.all)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun networkModulesTest() {
        val moshiExpected: Moshi by inject()
        val retrofitExpected: Retrofit by inject()

        Truth.assertThat(moshiExpected).isNotNull()
        Truth.assertThat(retrofitExpected).isNotNull()
    }

    @Test
    fun apiModulesTest() {
        val apiExpected: ScoreApi by inject()

        Truth.assertThat(apiExpected).isNotNull()
    }

    @Test
    fun repositoryModulesTest() {
        val repositoryExpected: ScoreRepositoryAbs by inject()

        Truth.assertThat(repositoryExpected).isInstanceOf(ScoreRepository::class.java)
    }

    @Test
    fun mapperModulesTest() {
        val mapperExpected: ScoreMapperAbs by inject()

        Truth.assertThat(mapperExpected).isInstanceOf(ScoreMapper::class.java)
    }

    @Test
    fun useCaseModulesTest() {
        val useCaseExpected: ScoreUseCaseAbs by inject()

        Truth.assertThat(useCaseExpected).isInstanceOf(ScoreUseCase::class.java)
    }

    @Test
    fun viewModelModulesTest() {
        val viewModelExpected: ScoreViewModel by inject()

        Truth.assertThat(viewModelExpected).isNotNull()
    }
}
