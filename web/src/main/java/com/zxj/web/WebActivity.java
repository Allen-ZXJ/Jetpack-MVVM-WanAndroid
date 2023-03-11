package com.zxj.web;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.web.databinding.AcWebBinding;

@Route(path = "/web/activity")
public class WebActivity extends BaseMvvmActivity<AcWebBinding,WebViewModel> {
    @Autowired(name = "title")
    public String TITLE;
    @Autowired(name = "webUrl")
    public String WEB_URL;
    private WebView webview;
    @Override
    public void initViews() {
        mViewModel.registerContext(this);
        ARouter.getInstance().inject(this);
        Toast.makeText(this, "You click the " + TITLE , Toast.LENGTH_SHORT).show();
        mbind.tvTitle.setText(TITLE);
        AgentWeb.with(this)
                .setAgentWebParent(mbind.parent,new LinearLayout.LayoutParams(-1,-1) )
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(WEB_URL);


    }

    @Override
    public void initEvents() {
        super.initEvents();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_web;
    }

    @Override
    protected int getVariableId() {
        return BR.webViewModel;
    }

    @Override
    protected void onDestroy() {
        if(webview!=null){
            mbind.parent.removeView(webview);
            webview = null;
        }
        super.onDestroy();

    }
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
           mbind.tvTitle.setText(title);
        }
    };
}