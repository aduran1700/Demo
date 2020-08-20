package com.adt.demo.ui.headlineslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adt.demo.data.model.ResponseState
import com.adt.demo.data.repository.HeadlineRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HeadlinesListViewModel @Inject constructor(
    private val repository: HeadlineRepository
): ViewModel() {

    private val headlineMutableLiveData = MutableLiveData<ResponseState>()
    val headlineResponse: LiveData<ResponseState> = headlineMutableLiveData
    private val disposable = CompositeDisposable()

    fun getHeadlineListData() {
        headlineMutableLiveData.postValue(ResponseState.Loading)
        disposable.add(repository.getHeadlineData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    when (it.size) {
                        0 -> headlineMutableLiveData.postValue(ResponseState.Empty)
                        else -> headlineMutableLiveData.postValue(ResponseState.Success(it))
                    }
                },
                {
                    headlineMutableLiveData.postValue(ResponseState.Error(
                        it.localizedMessage
                    ))
                }
            )
        )
    }

    fun onViewDestroy() = disposable.clear()

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}