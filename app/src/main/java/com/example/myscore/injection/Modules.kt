package com.example.myscore.injection

import com.example.myscore.BuildConfig
import com.example.myscore.data.api.ScoreApi
import com.example.myscore.data.repository.ScoreRepository
import com.example.myscore.data.repository.abs.ScoreRepositoryAbs
import com.example.myscore.domain.mapper.ScoreMapper
import com.example.myscore.domain.mapper.abs.ScoreMapperAbs
import com.example.myscore.domain.usecase.ScoreUseCase
import com.example.myscore.domain.usecase.abs.ScoreUseCaseAbs
import com.example.myscore.domain.viewmodel.ScoreViewModel
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*


object Modules {
    private val network = module {
        single {
            Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .build()
        }

        single {
            Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(get()))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                    .client(
                            OkHttpClient.Builder()
                                    .addInterceptor(OkHttpProfilerInterceptor())
                                    .build()
                    )
                    .build()
        }
    }

    private val api = module {
        single {
            val retrofit: Retrofit = get()
            retrofit.create(ScoreApi::class.java)
        }
    }

    private val repository = module {
        single<ScoreRepositoryAbs> {
            ScoreRepository(
                    api = get()
            )
        }
    }
    private val mapper = module {
        single<ScoreMapperAbs> {
            ScoreMapper()
        }
    }
    private val useCase = module {
        single<ScoreUseCaseAbs> {
            ScoreUseCase(
                mapper = get(),
                repository = get()
            )
        }
    }

    private val viewModel = module {
        viewModel {
            ScoreViewModel(
                useCase = get()
            )
        }
    }
    var all = listOf(
        network,
        api,
        repository,
        mapper,
        useCase,
        viewModel
    )
}