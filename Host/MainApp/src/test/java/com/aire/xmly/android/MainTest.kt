package com.aire.xmly.android

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


/**
 * Created by ZhuPeipei on 2021/3/29 21:30.
 */
object MainTest {
    const val TAG = "zimotag-main"

    @JvmStatic
    fun main(args: Array<String>) {
        println("abc")
        Observable.create(ObservableOnSubscribe<Int> { e ->
            Log.e(TAG, "Observable thread is : " + Thread.currentThread().name)
            e.onNext(1)
            e.onComplete()
        }).subscribeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(object : Consumer<Int> {
                @Throws(Exception::class)
                override fun accept(integer: Int) {
                    Log.e(
                        TAG,
                        "After observeOn(mainThread)，Current thread is " + Thread.currentThread()
                            .name
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