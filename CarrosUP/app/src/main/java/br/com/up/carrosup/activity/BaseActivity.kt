package br.com.up.carrosup.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Created by Franklyn on 19/08/2017.
 */

open class BaseActivity : AppCompatActivity() {
    protected val context: Context get() = this
}