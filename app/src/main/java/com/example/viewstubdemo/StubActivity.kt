package com.example.viewstubdemo

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewstubdemo.databinding.ActivityStubBinding

class StubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStubBinding

    private var inflatedView: View? = null
    private var stubImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setOnButtonListener()
    }

    private fun ActivityStubBinding.setOnButtonListener() {
        showViewBtn.setOnClickListener {
            try {
                inflatedView = viewStub.inflate()
                //调用其inflate()的时候，其布局属性android:layout=”@layout/布局”将会替换这个ViewStub标签，这个时候我们使用findViewById将获取这个ViewStub对象为空
            } catch (e: Exception) {
                viewStub.visibility = View.VISIBLE
            }

            stubImageView = inflatedView?.findViewById(R.id.stubImg) as ImageView
            stubImageView?.setImageResource(R.mipmap.ic_launcher)
        }

        updateViewBtn.setOnClickListener {
            if (inflatedView?.visibility == View.VISIBLE) {
                stubImageView?.setImageResource(R.drawable.ic_launcher_background)
                Toast.makeText(this@StubActivity, "图片已更换", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@StubActivity, "请先显示图片，再点击按钮", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        hideViewBtn.setOnClickListener {
            inflatedView?.visibility = View.GONE
        }
    }
}