package com.example.viewstubdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewstubdemo.databinding.ActivityStubBinding
import com.example.viewstubdemo.databinding.LayoutStubBinding

class StubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStubBinding
    private lateinit var stub1Binding: LayoutStubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.initView()
        binding.setOnButtonListener()
    }

    private fun ActivityStubBinding.initView() {
        viewStub.apply {
            setOnInflateListener { _, view ->
                stub1Binding = LayoutStubBinding.bind(view)
            }
            inflate() //调用其inflate()的时候，其布局属性android:layout=”@layout/布局”将会替换这个ViewStub标签，这个时候我们使用findViewById将获取这个ViewStub对象为空
        }
    }

    private fun ActivityStubBinding.setOnButtonListener() {
        showViewBtn.setOnClickListener {
            stub1Binding.stubLayout1.visibility = View.VISIBLE
            stub1Binding.stubImg.setImageResource(R.mipmap.ic_launcher)
        }

        updateViewBtn.setOnClickListener {
            if (stub1Binding.stubLayout1.visibility == View.VISIBLE) {
                stub1Binding.stubImg.setImageResource(R.drawable.ic_launcher_background)
                Toast.makeText(this@StubActivity, "图片已更换", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@StubActivity, "请先显示图片，再点击按钮", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        hideViewBtn.setOnClickListener {
            stub1Binding.stubLayout1.visibility = View.GONE
        }
    }
}