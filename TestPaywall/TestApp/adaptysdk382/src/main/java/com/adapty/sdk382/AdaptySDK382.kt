package com.adapty.sdk382
import android.app.Activity
import android.content.Context
import com.adapty.Adapty
import com.adapty.errors.AdaptyError
import com.adapty.internal.crossplatform.CrossplatformHelper
import com.adapty.internal.crossplatform.ui.AdaptyUiEvent
import com.adapty.models.AdaptyConfig
import com.adapty.models.AdaptyPaywall
import com.adapty.models.AdaptyPaywallProduct
import com.adapty.models.AdaptyProfile
import com.adapty.models.AdaptyPurchaseParameters
import com.adapty.models.AdaptyPurchaseResult
import com.adapty.ui.AdaptyPaywallInsets
import com.adapty.ui.AdaptyUI
import com.adapty.ui.listeners.AdaptyUiEventListener
import com.adapty.utils.AdaptyLogLevel
import com.adapty.utils.AdaptyResult
import com.adapty.utils.TimeInterval
import com.adapty.utils.seconds


class AdaptySDK382 {

    public final fun InitAdapty(context: Context, AdaptyId: String) {

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

            //var vc = AdaptyUI.getViewConfiguration()
            //var tt = AdaptyUI.getPaywallView()

            /*
        var helper = CrossplatformHelper.shared;
        helper.onMethodCall(
            "com.yourpackage.AdaptyAndroidHelper",
            "showPaywall",
            new object[] { activity, "your_paywall_id" });
        )
        */

        }

        /*
    public final fun InitAdapty(ApiId: String) {
        /*
        Adapty.activate(
            applicationContext,
            AdaptyConfig.Builder(ApiId)
                .withObserverMode(false) //default false
                // .withCustomerUserId(customerUserId) //default null
                .withIpAddressCollectionDisabled(false) //default false
                .withAdIdCollectionDisabled(false) // default false
                .build()
        )
        */

    }
    */
    }
}