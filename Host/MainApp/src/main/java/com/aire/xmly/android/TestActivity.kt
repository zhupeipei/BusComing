package com.aire.xmly.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class TestActivity : AppCompatActivity() {
    companion object {
        const val TAG = "zimotag-main"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        Log.e(TAG, "here start thread is : " + Thread.currentThread().name)
        Observable.create(ObservableOnSubscribe<Int> { e ->
            Log.e(TAG, "Observable thread is : " + Thread.currentThread().name)
            e.onNext(1)
            e.onComplete()
        })
//            .subscribeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(object : Consumer<Int> {
                @Throws(Exception::class)
                override fun accept(integer: Int) {
                    Log.e(
                        TAG,
                        "After observeOn(mainThread)，Current thread is " + Thread.currentThread().name
                    )
                }
            })
            .observeOn(Schedulers.io())
            .subscribe(object : Consumer<Int> {
                @Throws(Exception::class)
                override fun accept(integer: Int) {
                    Log.e(
                        TAG,
                        "After observeOn(io)，Current thread is " + Thread.currentThread().name
                    )
                }
            })
    }
}