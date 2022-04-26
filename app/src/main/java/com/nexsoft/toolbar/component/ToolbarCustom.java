package com.nexsoft.toolbar.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.nexsoft.toolbar.R;

public class ToolbarCustom extends AppBarLayout {
    public static final int TYPE_TITLE_BACK_BUTTON = 1;
    public static final int TYPE_TITLE_BACK_BUTTON_MENU = 2;
    public static final int TYPE_ONLINE_STATUS = 3;
    public static final int TYPE_SEARCH_BAR = 4;
    public static final int TYPE_TITLE_ONLY = 5;
    public static final int TYPE_TITLE_HAMBURGER = 6;

    private static final String DEFAULT_WHITE = "#FFFFFFFF";
    private static final String ONLINE = "Online";
    private static final String OFFLINE = "Offline";
    private static final int MAX_SEARCH_CHAR = 30;
    private static final int DEFAULT_HAMBURGER_ICON = R.drawable.ic_hamburger;
    private static final int DEFAULT_BACK_BUTTON_ICON = R.drawable.ic_back_button;
    private static final int DEFAULT_NO_AVATAR = R.drawable.ic_no_avatar;
    private MaterialToolbar toolbar;
    private boolean centeredTitle;
    private MaterialCardView btnBack;
    private MaterialCardView btnCart;
    private TextView tvOnlineStatus;
    private ImageView ivMagnifier;
    private int hamburger;
    private int toolbarType;
    private int onlineType;
    private boolean enableCart;
    private String fullName = "";
    private TextView tvFullName;
    private int onlineStatus;
    private TextInputEditText edtSearch;
    private String titleText = "";

    public ToolbarCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttr(context, attrs);
        initToolbar(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.ToolbarCustom, 0, 0);
        titleText = values.getString(R.styleable.ToolbarCustom_toolbar_title);
        if (titleText == null) titleText = "";
        centeredTitle = values.getBoolean(R.styleable.ToolbarCustom_centered_title, false);
        hamburger = values.getResourceId(R.styleable.ToolbarCustom_hamburger_icon, DEFAULT_HAMBURGER_ICON);
        toolbarType = values.getInt(R.styleable.ToolbarCustom_toolbar_type, TYPE_SEARCH_BAR);
        enableCart = values.getBoolean(R.styleable.ToolbarCustom_enable_cart, false);
        onlineType = values.getInt(R.styleable.ToolbarCustom_online_type, 1);
        onlineStatus = values.getInt(R.styleable.ToolbarCustom_online_status, 1);
        fullName = values.getString(R.styleable.ToolbarCustom_full_name);
        if (fullName == null) fullName = "";
        values.recycle();
    }

    private void init() {
        int colorInt = Color.parseColor(DEFAULT_WHITE);
        ColorStateList csl = ColorStateList.valueOf(colorInt);
        setBackgroundTintList(csl);
    }

    private void initToolbar(Context context) {
        toolbar = new MaterialToolbar(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbar.setLayoutParams(layoutParams);

        switch (toolbarType) {
            case TYPE_TITLE_BACK_BUTTON:
                setTypeTitleBackButton(toolbar);
                break;
            case TYPE_TITLE_BACK_BUTTON_MENU:
                setTypeTitleBackButtonMenu(toolbar);
                break;
            case TYPE_ONLINE_STATUS:
                setTypeOnlineStatus(toolbar, context);
                break;
            case TYPE_SEARCH_BAR:
                setTypeSearchBar(toolbar, context);
                break;
            case TYPE_TITLE_ONLY:
                setTypeTitleOnly(toolbar);
                break;
            case TYPE_TITLE_HAMBURGER:
                setTypeTitleHamburger(toolbar);
                break;
            default:
                toolbar.setTitle(titleText);
                toolbar.setTitleCentered(centeredTitle);
                toolbar.setNavigationIcon(hamburger);
                break;
        }
        addView(toolbar);
    }

    private void setTypeTitleBackButton(@NonNull MaterialToolbar toolbar) {
        toolbar.setTitle(titleText);
        toolbar.setTitleCentered(centeredTitle);
        toolbar.setNavigationIcon(DEFAULT_BACK_BUTTON_ICON);
    }

    private void setTypeTitleBackButtonMenu(@NonNull MaterialToolbar toolbar) {
        toolbar.setTitle(titleText);
        toolbar.setTitleCentered(centeredTitle);
        toolbar.setOverflowIcon(AppCompatResources.getDrawable(toolbar.getContext(), R.drawable.ic_menu_button));
        toolbar.setNavigationIcon(DEFAULT_BACK_BUTTON_ICON);
    }

    private void setTypeOnlineStatus(@NonNull MaterialToolbar toolbar, Context context) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationIcon(DEFAULT_BACK_BUTTON_ICON);
        toolbar.setOverflowIcon(AppCompatResources.getDrawable(toolbar.getContext(), R.drawable.ic_menu_button));

        // Vertical Linear Layout for username and online status
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams llLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llLayout.setMarginEnd(convertDpToPx(40));
        linearLayout.setOrientation(VERTICAL);

        // Add textView for full name
        tvFullName = new TextView(context);
        LinearLayout.LayoutParams fullNameLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvFullName.setLayoutParams(fullNameLayout);
        tvFullName.setText(fullName);
        tvFullName.setTypeface(tvFullName.getTypeface(), Typeface.BOLD);

        // Add textView for online status
        tvOnlineStatus = new TextView(context);
        LinearLayout.LayoutParams onlineLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvOnlineStatus.setLayoutParams(onlineLayout);
        if (onlineStatus == 1) tvOnlineStatus.setText(ONLINE);
        else tvOnlineStatus.setText(OFFLINE);

        if (onlineType == 1) {
            ShapeableImageView onlineAvatar = new ShapeableImageView(new ContextThemeWrapper(context,
                    R.style.ShapeAppearanceOverlay_App_RoundedRectangle));
            LinearLayout.LayoutParams onlineAvatarLayout = new LinearLayout
                    .LayoutParams(convertDpToPx(34), convertDpToPx(34));
            onlineAvatarLayout.setMarginEnd(convertDpToPx(8));
            onlineAvatar.setLayoutParams(onlineAvatarLayout);
            onlineAvatar.setImageResource(DEFAULT_NO_AVATAR);

            // Set gravity
            llLayout.gravity = Gravity.START;
            linearLayout.setLayoutParams(llLayout);
            linearLayout.setGravity(Gravity.START);

            linearLayout.addView(tvFullName);
            linearLayout.addView(tvOnlineStatus);

            toolbar.addView(onlineAvatar);
        } else {
            // Set gravity
            llLayout.gravity = Gravity.CENTER;
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayout.setLayoutParams(llLayout);

            linearLayout.addView(tvFullName);
            linearLayout.addView(tvOnlineStatus);
        }
        toolbar.addView(linearLayout);
    }

    private void setTypeSearchBar(@NonNull MaterialToolbar toolbar, Context context) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(null);

        // Card for back button
        btnBack = new MaterialCardView(context);
        LinearLayout.LayoutParams btnBackLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnBackLayout.gravity = Gravity.START;
        btnBack.setLayoutParams(btnBackLayout);
        btnBack.setRadius(120);
        btnBack.setCardElevation(0);
        btnBack.setClickable(true);
        btnBack.setFocusable(true);

        // ImageView for back button
        ImageView ivBack = new ImageView(context);
        LinearLayout.LayoutParams ivBackLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivBack.setLayoutParams(ivBackLayout);
        ivBack.setContentDescription("Back Button");
        ivBack.setPadding(convertDpToPx(10), convertDpToPx(10), convertDpToPx(10), convertDpToPx(10));
        ivBack.setImageResource(R.drawable.ic_back_button);
        btnBack.addView(ivBack);
        toolbar.addView(btnBack);

        // Linear Layout for search and cart
        LinearLayout llSearchCart = new LinearLayout(context);
        LinearLayout.LayoutParams searchCartLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llSearchCart.setOrientation(HORIZONTAL);
        llSearchCart.setLayoutParams(searchCartLayout);

        // Card for searchView box
        MaterialCardView searchViewBox = new MaterialCardView(context);
        LinearLayout.LayoutParams searchViewBoxLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0F);
        searchViewBoxLayout.setMargins(convertDpToPx(4), convertDpToPx(8), convertDpToPx(16), convertDpToPx(8));
        searchViewBox.setLayoutParams(searchViewBoxLayout);
        searchViewBox.setRadius(convertDpToPx(8));
        searchViewBox.setStrokeColor(Color.parseColor("#CACCCF"));
        searchViewBox.setStrokeWidth(1);
        searchViewBox.setCardElevation(0);

        // Linear layout for search edit text
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams llLayoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setLayoutParams(llLayoutParams);

        // TextField
        edtSearch = new TextInputEditText(context);
        LinearLayout.LayoutParams edtSearchLayout = new LinearLayout
                .LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0F);
        edtSearch.setLayoutParams(edtSearchLayout);
        edtSearch.setBackground(null);
        edtSearch.setHint("search-xs");
        edtSearch.setSingleLine(true);
        edtSearch.setInputType(InputType.TYPE_CLASS_TEXT);
        edtSearch.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edtSearch.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_SEARCH_CHAR)});
        edtSearch.setPadding(convertDpToPx(16), convertDpToPx(6), convertDpToPx(16), convertDpToPx(6));
        linearLayout.addView(edtSearch);

        // ImageView for magnifier (search icon)
        ivMagnifier = new ImageView(context);
        LinearLayout.LayoutParams ivMagnifierLayout = new LinearLayout
                .LayoutParams(convertDpToPx(18), convertDpToPx(18));
        ivMagnifierLayout.gravity = Gravity.CENTER_VERTICAL;
        ivMagnifierLayout.setMarginEnd(convertDpToPx(16));
        ivMagnifier.setLayoutParams(ivMagnifierLayout);
        ivMagnifier.setContentDescription("Search Icon");
        ivMagnifier.setClickable(true);
        ivMagnifier.setFocusable(true);
        ivMagnifier.setImageResource(R.drawable.ic_search);
        linearLayout.addView(ivMagnifier);
        searchViewBox.addView(linearLayout);
        llSearchCart.addView(searchViewBox);

        // Button for cart
        btnCart = new MaterialCardView(context);
        LinearLayout.LayoutParams btnCartLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnCartLayout.gravity = Gravity.CENTER | Gravity.END;
        btnCartLayout.setMarginEnd(convertDpToPx(16));
        btnCart.setLayoutParams(btnCartLayout);
        btnCart.setRadius(120);
        btnCart.setCardElevation(0);
        btnCart.setClickable(true);
        btnCart.setFocusable(true);
        if (enableCart) btnCart.setVisibility(VISIBLE);
        else btnCart.setVisibility(GONE);

        // Cart
        ImageView ivCart = new ImageView(context);
        LinearLayout.LayoutParams cartLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivCart.setLayoutParams(cartLayout);
        ivCart.setImageResource(R.drawable.ic_cart);
        ivCart.setPadding(convertDpToPx(10), convertDpToPx(10), convertDpToPx(10), convertDpToPx(10));
        btnCart.addView(ivCart);
        llSearchCart.addView(btnCart);

        toolbar.addView(llSearchCart);
    }

    private void setTypeTitleOnly(@NonNull MaterialToolbar toolbar) {
        toolbar.setTitle(titleText);
        toolbar.setTitleCentered(centeredTitle);
        toolbar.setNavigationIcon(null);
    }

    private void setTypeTitleHamburger(@NonNull MaterialToolbar toolbar) {
        toolbar.setTitle(titleText);
        toolbar.setTitleCentered(centeredTitle);
        toolbar.setNavigationIcon(hamburger);
    }

    public MaterialToolbar getToolbarView() {
        return toolbar;
    }

    public void setCenteredTitle(boolean isCentered) {
        toolbar.setTitleCentered(isCentered);
    }

    public void setToolbarTitle(String toolbarTitle) {
        if (toolbarTitle != null) toolbar.setTitle(toolbarTitle);
    }

    public void setHamburgerIcon(int drawableResource) {
        toolbar.setNavigationIcon(drawableResource);
    }

    public void setEnableCart(boolean isEnable) {
        if (isEnable && btnBack != null) btnCart.setVisibility(VISIBLE);
        else if (btnCart != null) btnCart.setVisibility(GONE);
    }

    public void setOnlineStatus(boolean isOnline) {
        if (isOnline && tvOnlineStatus != null) tvOnlineStatus.setText(ONLINE);
        else if (tvOnlineStatus != null) tvOnlineStatus.setText(OFFLINE);
    }

    public void setFullName(String fullName) {
        if (fullName != null && tvFullName != null) tvFullName.setText(fullName);
    }

    private int convertDpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5F);
    }

    public void onSubmitQuery(@NonNull SearchInterface searchInterface) {
        if (edtSearch != null && edtSearch.getText() != null) {

            edtSearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String queryText = edtSearch.getText().toString();
                    searchInterface.onQuerySubmit(queryText);

                    // Hide soft keyboard
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    return true;
                }
                return false;
            });

            ivMagnifier.setOnClickListener(view -> {
                String queryText = edtSearch.getText().toString();
                searchInterface.onQuerySubmit(queryText);

                // Hide soft keyboard
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            });
        }
    }

    public void onNavClickListener(@NonNull OnClick onClick) {
        if (toolbarType == TYPE_SEARCH_BAR) {
            btnBack.setOnClickListener(onClick::onClickListener);
        } else {
            toolbar.setNavigationOnClickListener(onClick::onClickListener);
        }
    }

    public void onCartClickListener(@NonNull OnClick onClick) {
        if (btnCart != null) btnCart.setOnClickListener(onClick::onClickListener);
    }

    public interface OnClick {
        void onClickListener(View view);
    }

    public interface SearchInterface {
        void onQuerySubmit(String query);
    }
}