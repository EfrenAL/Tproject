package app.tier.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiUtil {
    fun <T : Any> successCall(data: T) = createCall(data)

    fun <T : Any> createCall(response: T) = MutableLiveData<T>().apply {
        value = response
    } as LiveData<T>

    fun <T: Any> createCallSuccess(data: T) = object : Call<T>{
        override fun enqueue(callback: Callback<T>?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isExecuted(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun clone(): Call<T> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun isCanceled(): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun cancel() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun request(): Request {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun execute(): Response<T> {
            return Response.success(data)
        }


    }


}
