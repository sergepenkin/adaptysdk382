package com.adapty.sdk382
import android.app.Activity
import android.content.Context
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.StackView
import com.adapty.Adapty
import com.adapty.errors.AdaptyError
import com.adapty.internal.crossplatform.CrossplatformHelper
import com.adapty.internal.crossplatform.EventCallback
import com.adapty.internal.crossplatform.ResultCallback
import com.adapty.internal.crossplatform.ui.AdaptyUiEvent
import com.adapty.internal.utils.getClassForNameOrNull
import com.adapty.models.AdaptyConfig
import com.adapty.models.AdaptyPaywall
import com.adapty.models.AdaptyPaywallProduct
import com.adapty.models.AdaptyProfile
//import com.adapty.models.AdaptyPurchaseParameters
import com.adapty.models.AdaptyPurchaseResult
import com.adapty.ui.AdaptyPaywallInsets
import com.adapty.ui.AdaptyPaywallView
import com.adapty.ui.AdaptyUI
import com.adapty.ui.listeners.AdaptyUiDefaultEventListener
import com.adapty.ui.listeners.AdaptyUiEventListener
import com.adapty.utils.AdaptyLogLevel
import com.adapty.utils.AdaptyResult
import com.adapty.utils.TimeInterval
import com.adapty.utils.seconds
import com.yourpackage.adaptysdk382.R

abstract class ResultCallBack : com.adapty.internal.crossplatform.ResultCallback<kotlin.String> {

}

interface Event<T> {
    fun onEvent(event: T)
}

val d: Event<String> = object : Event<String> {
    override fun onEvent(event: String) {
        println(event)
    }
}

class AdaptySDK382
{
    private var _products: List<AdaptyPaywallProduct>? = null;
    private var helper: CrossplatformHelper? = null;
    private var activity: Activity? = null;

    private var eventListener_0 : com.adapty.internal.crossplatform.EventCallback<String>? = null;

    private var paywall: String = "";
    private var paywallview: String = "";
    private var paywallviewid: String = "";
    private var paywallviewres : String = "";

    private var paywallresult: com.adapty.internal.crossplatform.ResultCallback<String>? = null;
    private var paywallviewresult: com.adapty.internal.crossplatform.ResultCallback<String>? = null;
    private var paywallshowresult: com.adapty.internal.crossplatform.ResultCallback<String>? = null;

    /*
    object: com.adapty.internal.crossplatform.ResultCallback<String> {
        override fun invoke(result: String) {
            if (result != null)
            {
                paywall = paywall_result.ToString();
                string res1 = paywall_result.ToString();
                string pw = res1.Remove(res1.Length - 1, 1).Remove(0, 11);
                //pw = pw.Remove(0, 11);

                val argument = "{'paywall':{pw}}";

                helper?.OnMethodCall(
                    argument,
                    "adapty_ui_create_paywall_view", paywallViewlHandler);
            }
        }
    }

    private val eventListener_2= object: com.adapty.internal.crossplatform.ResultCallback<String> {
        override fun invoke(result: String) {
            var _name  = result;
        }
    }

    private val eventListener_3= object: com.adapty.internal.crossplatform.ResultCallback<String> {
        override fun invoke(result: String) {
            var _name  = result;
        }
    }
*/
    private val eventListener1= object: AdaptyUiDefaultEventListener() {
        /**
         * You can override more methods if needed
         */
        override fun onRestoreSuccess(
            profile: AdaptyProfile,
            context: Context,
        ) {
            if (profile.accessLevels["premium"]?.isActive == true) {
                //parentFragmentManager.popBackStack()
            }
        }
    }

    public final fun InitAdapty(context: Context, activity1: Activity , AdaptyId: String) {

        //public const string AdaptyId = "public_live_uXkrMIHl.6YWa7VOzEhya9jOE8VkU";
        //public const string PaywallID_A = "paywall_base";
        //public const string PaywallID_NEW = "paywall_new";

        Adapty.logLevel = AdaptyLogLevel.VERBOSE;

        Adapty.activate(
                context,
            AdaptyConfig.Builder(AdaptyId)//"public_live_uXkrMIHl.6YWa7VOzEhya9jOE8VkU")
                .withObserverMode(false) //default false
                // .withCustomerUserId(customerUserId) //default null
                .withIpAddressCollectionDisabled(false) //default false
                .withAdIdCollectionDisabled(false) // default false
                .build()
        )
        
        AdaptyUI.clearMediaCache(AdaptyUI.ClearCacheStrategy.CLEAR_ALL)

        activity = activity1;

        eventListener_0 = object : com.adapty.internal.crossplatform.EventCallback<String>
        {
            override fun invoke(name: String, data: String) {
                var _name  = name;
            }
        }

        CrossplatformHelper.init(
            context, eventListener_0 as EventCallback);


        CrossplatformHelper.shared.setActivity(activity1 as com.adapty.internal.crossplatform.ActivityProvider );

        helper = CrossplatformHelper.shared;


    }

    public final fun DisplayPaywallOverHelper(PaywallId: String, activity1: Activity )  {

        activity = activity1;

        paywallshowresult= object: com.adapty.internal.crossplatform.ResultCallback<String> {
            override fun invoke(showpaywallresult: String) {
                if (showpaywallresult != null)
                {
                    paywallviewres = showpaywallresult;
                }
            }
        }

        paywallviewresult = object: com.adapty.internal.crossplatform.ResultCallback<String> {
            override fun invoke(pwviewresult: String) {
                    if (pwviewresult != null)
                    {
                        paywallview = pwviewresult;
                        val Id: String = pwviewresult.substring(18, 54);

                        //string argument = $"{{\"id\":\"{Id}\"}}";
                        val  argument : String  = "{id:${Id}}";
                        paywallviewid = argument;

                        helper?.onMethodCall(
                            argument,
                            "adapty_ui_present_paywall_view",
                            onResult = paywallshowresult as ResultCallback<String>);

                    }
                }
        }

        paywallresult = object: com.adapty.internal.crossplatform.ResultCallback<String> {
            override fun invoke(paywall_result: String) {
                paywall = paywall_result;
                val pw : String  = paywall_result.substring(11,paywall_result.length - 1 );
                val argument = "{'paywall':${pw}}";
                helper?.onMethodCall(
                    argument,
                    "adapty_ui_create_paywall_view",
                    onResult = paywallviewresult as ResultCallback<String>
                );
            }
        }
            helper?.onMethodCall(
        "{'placement_id':'paywall_android'}",
        "get_paywall",
        onResult = paywallresult as ResultCallback<String>);
}

public final fun DisplayPaywall(PaywallId: String, activity1: Activity) {
Adapty.getPaywall(PaywallId, locale = "en", loadTimeout = 10.seconds) { result ->
    when (result) {
        is AdaptyResult.Success -> {
            val paywall = result.value
            // the requested paywall

            if (paywall.hasViewConfiguration) {
                AdaptyUI.getViewConfiguration(paywall, loadTimeout = 10.seconds) { result ->
                    when (result) {
                        is AdaptyResult.Success -> {
                            val viewConfiguration = result.value
                            // use loaded configuration

                            val paywallView = AdaptyUI.getPaywallView(
                                activity1,
                                viewConfiguration = viewConfiguration,
                                products = null, // Optional: pre-fetched products
                                eventListener = object : AdaptyUiEventListener {
                                    fun onEvent(event: AdaptyUiEvent) {

                                    }

                                    override fun onActionPerformed(
                                        action: AdaptyUI.Action,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onAwaitingPurchaseParams(
                                        product: AdaptyPaywallProduct,
                                        context: Context,
                                        onPurchaseParamsReceived: AdaptyUiEventListener.PurchaseParamsCallback
                                    ): AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onLoadingProductsFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ): Boolean {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallClosed() {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallShown(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onProductSelected(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFailure(
                                        error: AdaptyError,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFinished(
                                        purchaseResult: AdaptyPurchaseResult,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseStarted(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRenderingError(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreStarted(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreSuccess(
                                        profile: AdaptyProfile,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }
                                }
                            )

                            paywallView.showPaywall(
                                viewConfiguration,
                                products = null,
                                eventListener = object : AdaptyUiEventListener {
                                    override fun onActionPerformed(
                                        action: AdaptyUI.Action,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onAwaitingPurchaseParams(
                                        product: AdaptyPaywallProduct,
                                        context: Context,
                                        onPurchaseParamsReceived: AdaptyUiEventListener.PurchaseParamsCallback
                                    ): AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked {
                                        return AdaptyUiEventListener.PurchaseParamsCallback
                                    }

                                    override fun onLoadingProductsFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ): Boolean {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallClosed() {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallShown(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onProductSelected(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFailure(
                                        error: AdaptyError,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFinished(
                                        purchaseResult: AdaptyPurchaseResult,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseStarted(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onRenderingError(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onRestoreFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreStarted(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreSuccess(
                                        profile: AdaptyProfile,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }
                                }
                                /*,
                                insets = TODO(),
                                personalizedOfferResolver = TODO(),
                                customAssets = TODO(),
                                tagResolver = TODO(),
                                timerResolver = TODO(),
                                observerModeHandler = TODO() */
                            )
                            //}
                            Adapty.logShowPaywall(paywall)
                        }

                        is AdaptyResult.Error -> {
                            val error = result.error
                        }
                    }
                }
            }

        }

        is AdaptyResult.Error -> {
            val error = result.error
            // handle the error
        }
    }
}
}


public final fun ShowPaywall(PaywallId: String, activity1: Activity, res : Int)  {
//Adapty.getPaywall(PaywallId, locale = "en", loadTimeout = 10.seconds) { result ->
Adapty.getPaywall(PaywallId, loadTimeout = 10.seconds) { result ->
    when (result) {
        is AdaptyResult.Success -> {
            val paywall = result.value
            // the requested paywall

            if (paywall.hasViewConfiguration) {
                AdaptyUI.getViewConfiguration(paywall, loadTimeout = 10.seconds) { result ->
                    when (result) {
                        is AdaptyResult.Success -> {
                            val viewConfiguration = result.value

                            val paywallView = AdaptyUI.getPaywallView(
                                activity1,
                                viewConfiguration = viewConfiguration,
                                products = null, // Optional: pre-fetched products
                                eventListener = object : AdaptyUiEventListener {
                                    fun onEvent(event: AdaptyUiEvent) {

                                    }

                                    override fun onActionPerformed(
                                        action: AdaptyUI.Action,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onAwaitingPurchaseParams(
                                        product: AdaptyPaywallProduct,
                                        context: Context,
                                        onPurchaseParamsReceived: AdaptyUiEventListener.PurchaseParamsCallback
                                    ): AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onLoadingProductsFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ): Boolean {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallClosed() {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallShown(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onProductSelected(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFailure(
                                        error: AdaptyError,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFinished(
                                        purchaseResult: AdaptyPurchaseResult,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseStarted(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRenderingError(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreStarted(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreSuccess(
                                        profile: AdaptyProfile,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }
                                }
                            )

                            Adapty.logShowPaywall(paywall)
                            activity1.runOnUiThread {

                            val parentLayout = activity1.findViewById<LinearLayout>(res)
                            parentLayout.addView(paywallView)

                            //parentLayout.invalidate();
/*
                            paywallView.showPaywall(
                                viewConfiguration,
                                products = null,
                                eventListener = object : AdaptyUiEventListener {
                                    override fun onActionPerformed(
                                        action: AdaptyUI.Action,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onAwaitingPurchaseParams(
                                        product: AdaptyPaywallProduct,
                                        context: Context,
                                        onPurchaseParamsReceived: AdaptyUiEventListener.PurchaseParamsCallback
                                    ): AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked {
                                        return AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked
                                    }

                                    override fun onLoadingProductsFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ): Boolean {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallClosed() {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallShown(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onProductSelected(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFailure(
                                        error: AdaptyError,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFinished(
                                        purchaseResult: AdaptyPurchaseResult,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseStarted(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onRenderingError(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        //TODO("Not yet implemented")
                                    }

                                    override fun onRestoreFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreStarted(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreSuccess(
                                        profile: AdaptyProfile,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }
                                }
                                /*,
                                insets = TODO(),
                                personalizedOfferResolver = TODO(),
                                customAssets = TODO(),
                                tagResolver = TODO(),
                                timerResolver = TODO(),
                                observerModeHandler = TODO() */
                            )
                            */
                            }

                            //}


                        }

                        is AdaptyResult.Error -> {
                            val error = result.error
                        }
                    }
                }
            }

        }

        is AdaptyResult.Error -> {
            val error = result.error
            // handle the error
        }
    }
}
}


public final fun ShowPaywall2(PaywallId: String, activity1: Activity, res : Int)  {

//Adapty.getPaywall(PaywallId, locale = "en", loadTimeout = 10.seconds) { result ->
Adapty.getPaywall(PaywallId, loadTimeout = 10.seconds) { result ->
    when (result) {
        is AdaptyResult.Success -> {
            val paywall = result.value
            // the requested paywall


            if (paywall.hasViewConfiguration) {

                Adapty.getPaywallProducts(paywall) { productResult ->
                    when (productResult) {
                        is AdaptyResult.Success -> {
                            _products = productResult.value;

                            /*
                            lastResponseResult.text =
                                "Paywall: $paywall\n\nProducts: ${productResult.value}"

                            Adapty.logShowPaywall(paywall)

                            (activity as? MainActivity)?.addFragment(
                                ProductListFragment.newInstance(
                                    paywall,
                                    productResult.value
                                ),
                                true
                            )
                            */


                            AdaptyUI.getViewConfiguration(
                                paywall,
                                loadTimeout = 10.seconds
                            ) { result ->
                                when (result) {
                                    is AdaptyResult.Success -> {
                                        val viewConfiguration = result.value
                                        activity1.runOnUiThread {

                            //val customTags = mapOf("USERNAME" to "Bruce", "CITY" to "Philadelphia")
                            val paywallView = AdaptyUI.getPaywallView(
                                activity1,
                                viewConfiguration = viewConfiguration,
                                products = null, // Optional: pre-fetched products
                                //eventListener = eventListener1
                                eventListener= object: AdaptyUiDefaultEventListener() {

                                    /**
                                     * You can override more methods if needed
                                     */
                                    override fun onPaywallClosed(): Unit {
                                    val str = "";
                                    /* compiled code */ }

                                    override fun onRestoreFailure(error: AdaptyError, context: Context): Unit
                                    {
                                        val err = error;
                                    /* compiled code */ }
                                    override fun onRestoreSuccess(
                                        profile: AdaptyProfile,
                                        context: Context,
                                    ) {
                                        if (profile.accessLevels["premium"]?.isActive == true) {
                                            //parentFragmentManager.popBackStack()
                                        }
                                    }
                                }


                                //tagResolver = { tag -> customTags[tag] }
                                /*
                                eventListener = object : AdaptyUiEventListener {
                                    fun onEvent(event: AdaptyUiEvent) {

                                    }

                                    override fun onActionPerformed(
                                        action: AdaptyUI.Action,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onAwaitingPurchaseParams(
                                        product: AdaptyPaywallProduct,
                                        context: Context,
                                        onPurchaseParamsReceived: AdaptyUiEventListener.PurchaseParamsCallback
                                    ): AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onLoadingProductsFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ): Boolean {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallClosed() {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPaywallShown(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onProductSelected(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFailure(
                                        error: AdaptyError,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseFinished(
                                        purchaseResult: AdaptyPurchaseResult,
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onPurchaseStarted(
                                        product: AdaptyPaywallProduct,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRenderingError(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreFailure(
                                        error: AdaptyError,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreStarted(context: Context) {
                                        TODO("Not yet implemented")
                                    }

                                    override fun onRestoreSuccess(
                                        profile: AdaptyProfile,
                                        context: Context
                                    ) {
                                        TODO("Not yet implemented")
                                    }
                                }
                                */

                            )
                            val parentLayout = activity1.findViewById<LinearLayout>(res)
                            parentLayout.addView(paywallView)
                            //parentLayout.invalidate();

                            //Adapty.logShowPaywall(paywall)
/*
                                val paywallView = activity1.findViewById<AdaptyPaywallView>(res)
                                            val customTags = mapOf("USERNAME" to "Bruce", "CITY" to "Philadelphia")

                                            paywallView.showPaywall(
                                                viewConfiguration,
                                                products = null,
                                                //eventListener = eventListener1
                                                //tagResolver = { tag -> customTags[tag] }
                                                eventListener = object :
                                                    AdaptyUiEventListener {
                                                    override fun onActionPerformed(
                                                        action: AdaptyUI.Action,
                                                        context: Context
                                                    ) {
                                                        //TODO("Not yet implemented")
                                                    }

                                                    override fun onAwaitingPurchaseParams(
                                                        product: AdaptyPaywallProduct,
                                                        context: Context,
                                                        onPurchaseParamsReceived: AdaptyUiEventListener.PurchaseParamsCallback
                                                    ): AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked {
                                                        return AdaptyUiEventListener.PurchaseParamsCallback.IveBeenInvoked
                                                    }

                                                    override fun onLoadingProductsFailure(
                                                        error: AdaptyError,
                                                        context: Context
                                                    ): Boolean {
                                                        TODO("Not yet implemented")
                                                    }

                                                    override fun onPaywallClosed() {
                                                        TODO("Not yet implemented")
                                                    }

                                                    override fun onPaywallShown(context: Context) {
                                                        TODO("Not yet implemented")
                                                    }

                                                    override fun onProductSelected(
                                                        product: AdaptyPaywallProduct,
                                                        context: Context
                                                    ) {
                                                        TODO("Not yet implemented")
                                                    }

                                                    override fun onPurchaseFailure(
                                                        error: AdaptyError,
                                                        product: AdaptyPaywallProduct,
                                                        context: Context
                                                    ) {
                                                        TODO("Not yet implemented")
                                                    }

                                                    override fun onPurchaseFinished(
                                                        purchaseResult: AdaptyPurchaseResult,
                                                        product: AdaptyPaywallProduct,
                                                        context: Context
                                                    ) {
                                                        //TODO("Not yet implemented")
                                                    }

                                                    override fun onPurchaseStarted(
                                                        product: AdaptyPaywallProduct,
                                                        context: Context
                                                    ) {
                                                        //TODO("Not yet implemented")
                                                    }

                                                    override fun onRenderingError(
                                                        error: AdaptyError,
                                                        context: Context
                                                    ) {
                                                        //TODO("Not yet implemented")
                                                    }

                                                    override fun onRestoreFailure(
                                                        error: AdaptyError,
                                                        context: Context
                                                    ) {
                                                        TODO("Not yet implemented")
                                                    }

                                                    override fun onRestoreStarted(context: Context) {
                                                        TODO("Not yet implemented")
                                                    }

                                                    override fun onRestoreSuccess(
                                                        profile: AdaptyProfile,
                                                        context: Context
                                                    ) {
                                                        TODO("Not yet implemented")
                                                    }
                                                }
                                            )
*/
                                        }

                                        //}


                                    }

                                    is AdaptyResult.Error -> {
                                        val error = result.error
                                    }
                                }
                            }
                        }

                        is AdaptyResult.Error -> {
                            //val error = result.error
                            // handle the error
                        }
                    }
                }}
            }
        is AdaptyResult.Error -> {
            val error = result.error
            // handle the error
        }
    }
}
}
    /*
    override fun invoke(): Activity? {
    return activity;
    }
    */

}