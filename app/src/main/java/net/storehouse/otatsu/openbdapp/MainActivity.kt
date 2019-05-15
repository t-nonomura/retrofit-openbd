package net.storehouse.otatsu.openbdapp

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.google.gson.Gson
import net.storehouse.otatsu.openbdapp.api.ApiClientManager
import net.storehouse.otatsu.openbdapp.databinding.ActivityMainBinding
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
    private val binding: ActivityMainBinding by lazy  {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val compositeSubscription = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeSubscription.clear()
        binding.searchButton.setOnClickListener {
            val isbn = binding.editText.text.toString()
            if (TextUtils.isEmpty(isbn)) return@setOnClickListener

            compositeSubscription.add(
                ApiClientManager.apiClient.getISBN(isbn)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        binding.responseText.text = Gson().toJson(it)
                    }
                    .doOnError {}
                    .doOnCompleted {}
                    .subscribe())
        }
    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }
}
