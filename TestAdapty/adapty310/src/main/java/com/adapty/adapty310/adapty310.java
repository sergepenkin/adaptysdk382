package com.adapty.adapty310;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adapty.Adapty;
import com.adapty.errors.AdaptyError;
import com.adapty.internal.crossplatform.ui.AdaptyUiEvent;
import com.adapty.models.AdaptyConfig;
import com.adapty.models.AdaptyPaywall;
import com.adapty.models.AdaptyPaywallProduct;
import com.adapty.models.AdaptyProfile;
import com.adapty.models.AdaptyPurchaseResult;
import com.adapty.ui.AdaptyCustomAsset;
import com.adapty.ui.AdaptyCustomAssets;
import com.adapty.ui.AdaptyPaywallInsets;
import com.adapty.ui.AdaptyPaywallView;
import com.adapty.ui.AdaptyUI;
import com.adapty.ui.listeners.AdaptyUiEventListener;
import com.adapty.ui.listeners.AdaptyUiObserverModeHandler;
import com.adapty.ui.listeners.AdaptyUiTagResolver;
import com.adapty.ui.listeners.AdaptyUiTimerResolver;
import com.adapty.utils.AdaptyLogLevel;
import com.adapty.utils.AdaptyResult;
import com.adapty.utils.TimeInterval;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.adapty.utils.seconds;
public class adapty310 {
    public final void InitAdapty(Context context, String AdaptyId) {

        //public const string AdaptyId = "public_live_uXkrMIHl.6YWa7VOzEhya9jOE8VkU";
        //public const string PaywallID_A = "paywall_base";
        //public const string PaywallID_NEW = "paywall_new";

        Adapty.setLogLevel( AdaptyLogLevel.VERBOSE );

        Adapty.activate(
                context,
                new AdaptyConfig.Builder(AdaptyId)//"public_live_uXkrMIHl.6YWa7VOzEhya9jOE8VkU")
                        .withObserverMode(false) //default false
                        // .withCustomerUserId(customerUserId) //default null
                        .withIpAddressCollectionDisabled(false) //default false
                        .withAdIdCollectionDisabled(false) // default false
                        .build()
        );
        AdaptyUI.clearMediaCache(AdaptyUI.ClearCacheStrategy.CLEAR_ALL);

    }

    public final void DisplayPaywall(String PaywallId, Activity activity1) {
        Adapty.getPaywall(PaywallId, "en", result -> {
            if (result instanceof AdaptyResult.Success) {
                AdaptyPaywall paywall = ((AdaptyResult.Success<AdaptyPaywall>) result).getValue();
                // the requested paywall

                if (!paywall.hasViewConfiguration()) {
                    // use your custom logic
                    return;
                }

                AdaptyUI.getViewConfiguration(paywall, TimeInterval.seconds(10), vcresult -> {
                    if (vcresult instanceof AdaptyResult.Success) {
                        AdaptyUI.LocalizedViewConfiguration viewConfiguration =
                                ((AdaptyResult.Success<AdaptyUI.LocalizedViewConfiguration>) vcresult).getValue();

                        AdaptyPaywallView paywallView =
                                new AdaptyPaywallView(activity1); //add to the view hierarchy if needed, or you receive it from xml

                        AdaptyUiEventListener eventListener = new AdaptyUiEventListener() {
                            @Override
                            public void onRestoreSuccess(@NonNull AdaptyProfile adaptyProfile, @NonNull Context context) {

                            }

                            @Override
                            public void onRestoreStarted(@NonNull Context context) {

                            }

                            @Override
                            public void onRestoreFailure(@NonNull AdaptyError adaptyError, @NonNull Context context) {

                            }

                            @Override
                            public void onRenderingError(@NonNull AdaptyError adaptyError, @NonNull Context context) {

                            }

                            @Override
                            public void onPurchaseStarted(@NonNull AdaptyPaywallProduct adaptyPaywallProduct, @NonNull Context context) {

                            }

                            @Override
                            public void onPurchaseFinished(@NonNull AdaptyPurchaseResult adaptyPurchaseResult, @NonNull AdaptyPaywallProduct adaptyPaywallProduct, @NonNull Context context) {

                            }

                            @Override
                            public void onPurchaseFailure(@NonNull AdaptyError adaptyError, @NonNull AdaptyPaywallProduct adaptyPaywallProduct, @NonNull Context context) {

                            }

                            @Override
                            public void onProductSelected(@NonNull AdaptyPaywallProduct adaptyPaywallProduct, @NonNull Context context) {

                            }

                            @Override
                            public void onPaywallShown(@NonNull Context context) {

                            }

                            @Override
                            public void onPaywallClosed() {

                            }

                            @Override
                            public boolean onLoadingProductsFailure(@NonNull AdaptyError adaptyError, @NonNull Context context) {
                                return false;
                            }

                            @NonNull
                            @Override
                            public PurchaseParamsCallback.IveBeenInvoked onAwaitingPurchaseParams(@NonNull AdaptyPaywallProduct adaptyPaywallProduct, @NonNull Context context, @NonNull PurchaseParamsCallback purchaseParamsCallback) {
                                return null;
                            }

                            @Override
                            public void onActionPerformed(@NonNull AdaptyUI.Action action, @NonNull Context context) {

                            }

                        };

                        AdaptyUiObserverModeHandler oh = new AdaptyUiObserverModeHandler() {
                            @Override
                            public void onPurchaseInitiated(@NonNull AdaptyPaywallProduct adaptyPaywallProduct, @NonNull AdaptyPaywall adaptyPaywall, @NonNull Context context, @NonNull PurchaseStartCallback purchaseStartCallback, @NonNull PurchaseFinishCallback purchaseFinishCallback) {

                            }
                        };
                        AdaptyUiTimerResolver tr = new AdaptyUiTimerResolver() {
                            @NonNull
                            @Override
                            public Date timerEndAtDate(@NonNull String s) {
                                return null;
                            }
                        };

                        //Map<String, String> customTags = new HashMap<>();
                        //customTags.put("USERNAME", "John");
                        AdaptyUiTagResolver tagResolver = new AdaptyUiTagResolver() {
                            @Nullable
                            @Override
                            public String replacement(@NonNull String s) {
                                return "";
                            }
                        };

                        //AdaptyCustomAssets ca = AdaptyCustomAssets.of(null, null);
                        AdaptyCustomAssets ca = AdaptyCustomAssets.of(null, null);
/*
                                viewConfiguration: AdaptyUI.LocalizedViewConfiguration,
                                products: List<AdaptyPaywallProduct>?,
                        eventListener: AdaptyUiEventListener,
                                insets: AdaptyPaywallInsets = AdaptyPaywallInsets.UNSPECIFIED,
                                customAssets: AdaptyCustomAssets = AdaptyCustomAssets.Empty,
                                tagResolver: AdaptyUiTagResolver = AdaptyUiTagResolver.DEFAULT,
                                timerResolver: AdaptyUiTimerResolver = AdaptyUiTimerResolver.DEFAULT,
                                observerModeHandler: AdaptyUiObserverModeHandler? = null,
    ) {
*/
                            paywallView.showPaywall( viewConfiguration,
                                null,
                                eventListener,
                                AdaptyPaywallInsets.UNSPECIFIED,
                                AdaptyCustomAssets.Empty,
                                AdaptyUiTagResolver.DEFAULT,
                                AdaptyUiTimerResolver.DEFAULT,
                                oh);

                        // use loaded configuration
                    } else if (vcresult instanceof AdaptyResult.Error) {
                        AdaptyError error = ((AdaptyResult.Error) vcresult).getError();
                        // handle the error
                    }
                });

            } else if (result instanceof AdaptyResult.Error) {
                AdaptyError error = ((AdaptyResult.Error) result).getError();
                // handle the error

            }
        });

/*
        Adapty.getPaywall(PaywallId, locale = "en", loadTimeout = 10.seconds) { result ->
                when (result) {
            is AdaptyResult.Success -> {
                val paywall = result.value
                // the requested paywall

                if (paywall. hasViewConfiguration) {
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
                            )

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
 */
    }
}
