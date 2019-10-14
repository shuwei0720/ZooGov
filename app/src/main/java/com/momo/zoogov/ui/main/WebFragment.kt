package com.momo.zoogov.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.momo.zoogov.R

private const val ARG_URL = "web_url"

class WebFragment : Fragment() {

    private var url: String? = null
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.frg_web, container, false)

        progressBar = root.findViewById(R.id.progress_bar)
        webView = root.findViewById(R.id.web_view)
        webView.loadUrl(url)

        webView.webViewClient = object: WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.INVISIBLE
            }
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return true
            }
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            WebFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URL, param)
                }
            }
    }
}
