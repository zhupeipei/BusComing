package com.aire.xmly.android.test

import android.util.Log
import com.aire.xmly.android.activity.TestActivity
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Created by ZhuPeipei on 2021/4/25 10:58.
 */
class NetTest {
    fun startTest() {
        //        test1()
        // 网络请求 rxjava结合retrofit
        test2()
    }

    private fun test2() {
        Log.d(TestActivity.TAG, "start 1")
        Observable
            .create(object : ObservableOnSubscribe<Response> {
                override fun subscribe(emitter: ObservableEmitter<Response>) {
                    Log.d(TestActivity.TAG, "subscribe thred in --- ${Thread.currentThread().name}")
                    val builder = Request.Builder()
                        .url("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9954957842414516568%22%7D&n_type=0&p_from=1")
                        .get()
                    val request = builder.build()
                    val call = OkHttpClient().newCall(request);
                    val response = call.execute();
                    emitter.onNext(response);
                }
            })
            .map(object : Function<Response, MobileAddress> {
                override fun apply(response: Response): MobileAddress? {
                    Log.d(TestActivity.TAG, "map thred in --- ${Thread.currentThread().name}")
                    if (response.isSuccessful) {
                        response.body?.let {
                            Log.d(TestActivity.TAG, "response ${it.string().substring(0, 10)} ...")
                            try {
                                return Gson().fromJson(it.charStream(), MobileAddress::class.java)
                            } catch (e: java.lang.Exception) {
                                e.printStackTrace()
                                return MobileAddress()
                            }
                        }
                    }
                    return null
                }
            })
            .observeOn(Schedulers.newThread())
            .doOnNext(object : Consumer<MobileAddress> {
                override fun accept(t: MobileAddress?) {
                    Log.d(TestActivity.TAG, "doOnNext thred in --- ${Thread.currentThread().name}")
                    Log.d(TestActivity.TAG, "doNext ${t}")
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<MobileAddress> {
                override fun accept(t: MobileAddress?) {
                    Log.d(
                        TestActivity.TAG,
                        "subscribe success thred in --- ${Thread.currentThread().name}"
                    )
                    Log.d(TestActivity.TAG, "result ${t}")
                }
            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable?) {
                    Log.d(
                        TestActivity.TAG,
                        "subscribe error thred in --- ${Thread.currentThread().name}"
                    )
                    Log.d(TestActivity.TAG, "error ${t?.message}")
                    t?.printStackTrace()
                }
            })
        Log.d(TestActivity.TAG, "start 2")
    }

    private fun test1() {
        Log.e(TestActivity.TAG, "here start thread is : " + Thread.currentThread().name)
        Observable.create(ObservableOnSubscribe<Int> { e ->
            Log.e(TestActivity.TAG, "Observable thread is : " + Thread.currentThread().name)
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
                        TestActivity.TAG,
                        "After observeOn(mainThread)，Current thread is " + Thread.currentThread().name
                    )
                }
            })
            .observeOn(Schedulers.io())
            .subscribe(object : Consumer<Int> {
                @Throws(Exception::class)
                override fun accept(integer: Int) {
                    Log.e(
                        TestActivity.TAG,
                        "After observeOn(io)，Current thread is " + Thread.currentThread().name
                    )
                }
            })
    }
}

class MobileAddress {}