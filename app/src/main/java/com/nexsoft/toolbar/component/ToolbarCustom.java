package com.nexsoft.toolbar.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
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
    private static final int MAX_SEARCH_CHAR = 30;
    private static final int DEFAULT_HAMBURGER_ICON = R.drawable.ic_hamburger;
    private static final int DEFAULT_BACK_BUTTON_ICON = R.drawable.ic_back_button;
    private MaterialToolbar toolbar;
    private boolean centeredTitle;
    private MaterialCardView btnBack;
    private ImageView ivMagnifier;
    private int hamburger;
    private int toolbarType;
    private TextInputEditText edtSearch;
    private String titleText = "";
    private String queryText = "";

    public ToolbarCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttr(context, attrs);
        initToolbar(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.ToolbarCustom, 0, 0);
        titleText = values.getString(R.styleable.ToolbarCustom_toolbar_title);
        if (titleText == null) {
            titleText = "";
        }
        centeredTitle = values.getBoolean(R.styleable.ToolbarCustom_centered_title, false);
        hamburger = values.getResourceId(R.styleable.ToolbarCustom_hamburger_icon, DEFAULT_HAMBURGER_ICON);
        toolbarType = values.getInt(R.styleable.ToolbarCustom_toolbar_type, TYPE_SEARCH_BAR);
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
                setTypeOnlineStatus(toolbar);
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

    private void setTypeOnlineStatus(@NonNull MaterialToolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(null);


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

        // Card for searchView box
        MaterialCardView searchViewBox = new MaterialCardView(context);
        LinearLayout.LayoutParams searchViewBoxLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchViewBoxLayout.gravity = Gravity.CENTER;
        searchViewBoxLayout.setMargins(convertDpToPx(24), convertDpToPx(8), convertDpToPx(16), convertDpToPx(8));
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

        // ImageView for back button
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
        toolbar.addView(searchViewBox);
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
        toolbar.setTitle(toolbarTitle);
    }

    public void setHamburgerIcon(int drawableResource) {
        toolbar.setNavigationIcon(drawableResource);
    }

    private int convertDpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5F);
    }

    public void onSubmitQuery(@NonNull SearchInterface searchInterface) {
        if (edtSearch.getText() != null) {
            queryText = edtSearch.getText().toString();

            edtSearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
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

    public interface OnClick {
        void onClickListener(View view);
    }

    public interface SearchInterface {
        void onQuerySubmit(String query);
    }
}