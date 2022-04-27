package com.nexsoft.toolbar.component;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.nexsoft.toolbar.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationBarCustom extends ConstraintLayout {
    private static final int DEFAULT_TEXT_SIZE = 11;
    private static final String DEFAULT_SELECTED_COLOR = "#9B3789";
    private static final String DEFAULT_UNSELECTED_COLOR = "#424242";
    private static final int MENU_ONE = 1;
    private static final int MENU_TWO = 2;
    private static final int MENU_THREE = 3;
    private static final int MENU_FOUR = 4;
    private static final int MENU_FIVE = 5;

    private final List<MaterialCardView> buttonList = new ArrayList<>();
    private final List<TextView> tvList = new ArrayList<>();
    private final List<ImageView> ivList = new ArrayList<>();
    private List<NavBarMenuModel> navBarMenuList = new ArrayList<>();
    private TextView tvOne;
    private ImageView ivOne;
    private MaterialCardView btnOne;
    private TextView tvTwo;
    private ImageView ivTwo;
    private MaterialCardView btnTwo;
    private TextView tvThree;
    private ImageView ivThree;
    private MaterialCardView btnThree;
    private TextView tvFour;
    private ImageView ivFour;
    private MaterialCardView btnFour;
    private TextView tvFive;
    private ImageView ivFive;
    private MaterialCardView btnFive;
    private int selectedMenu;
    private int selectedColor;
    private int unselectedColor;
    private View selectedMenuView;

    public NavigationBarCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initFirstMenu(context);
        initSecondMenu(context);
        initThirdMenu(context);
        initFourthMenu(context);
        initFifthMenu(context);
        initConstraintSet();

        initColor();
    }

    private void initFirstMenu(Context context) {
        // Menu title
        tvOne = new TextView(context);
        LinearLayout.LayoutParams tvOneLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvOne.setTextSize(DEFAULT_TEXT_SIZE);
        tvOne.setText("Scan");
        tvOne.setLayoutParams(tvOneLayout);

        // Menu icon
        ivOne = new ImageView(context);
        LinearLayout.LayoutParams ivOneLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivOne.setImageResource(R.drawable.ic_scan);
        ivOne.setLayoutParams(ivOneLayout);

        // Linear layout for holding title and icon vertically
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams llLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(convertDpToPx(5), convertDpToPx(5), convertDpToPx(5), convertDpToPx(5));
        linearLayout.setLayoutParams(llLayout);

        linearLayout.addView(ivOne);
        linearLayout.addView(tvOne);

        // Card for holding the linear layout
        btnOne = new MaterialCardView(context);
        btnOne.setId(View.generateViewId());
        btnOne.setClickable(true);
        btnOne.setFocusable(true);
        btnOne.setRadius(convertDpToPx(6));
        btnOne.setCardElevation(0);
        btnOne.addView(linearLayout);

        btnOne.setVisibility(GONE);
        addView(btnOne);

        // Add to list
        tvList.add(tvOne);
        ivList.add(ivOne);
        buttonList.add(btnOne);
    }

    private void initSecondMenu(Context context) {
        // Menu title
        tvTwo = new TextView(context);
        LinearLayout.LayoutParams tvTwoLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTwo.setTextSize(DEFAULT_TEXT_SIZE);
        tvTwo.setText("Upload");
        tvTwo.setLayoutParams(tvTwoLayout);

        // Menu icon
        ivTwo = new ImageView(context);
        LinearLayout.LayoutParams ivTwoLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivTwo.setImageResource(R.drawable.ic_upload);
        ivTwo.setLayoutParams(ivTwoLayout);

        // Linear layout for holding title and icon vertically
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams llLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(convertDpToPx(5), convertDpToPx(5), convertDpToPx(5), convertDpToPx(5));
        linearLayout.setLayoutParams(llLayout);

        linearLayout.addView(ivTwo);
        linearLayout.addView(tvTwo);

        // Card for holding the linear layout
        btnTwo = new MaterialCardView(context);
        btnTwo.setId(View.generateViewId());
        btnTwo.setClickable(true);
        btnTwo.setFocusable(true);
        btnTwo.setRadius(convertDpToPx(6));
        btnTwo.setCardElevation(0);
        btnTwo.addView(linearLayout);

        btnTwo.setVisibility(GONE);
        addView(btnTwo);

        // Add to list
        tvList.add(tvTwo);
        ivList.add(ivTwo);
        buttonList.add(btnTwo);
    }

    private void initThirdMenu(Context context) {
        // Menu title
        tvThree = new TextView(context);
        LinearLayout.LayoutParams tvThreeLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvThree.setTextSize(DEFAULT_TEXT_SIZE);
        tvThree.setText("Create");
        tvThree.setLayoutParams(tvThreeLayout);

        // Menu icon
        ivThree = new ImageView(context);
        LinearLayout.LayoutParams ivThreeLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivThree.setImageResource(R.drawable.ic_create);
        ivThree.setLayoutParams(ivThreeLayout);

        // Linear layout for holding title and icon vertically
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams llLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(convertDpToPx(5), convertDpToPx(5), convertDpToPx(5), convertDpToPx(5));
        linearLayout.setLayoutParams(llLayout);

        linearLayout.addView(ivThree);
        linearLayout.addView(tvThree);

        // Card for holding the linear layout
        btnThree = new MaterialCardView(context);
        btnThree.setId(View.generateViewId());
        btnThree.setClickable(true);
        btnThree.setFocusable(true);
        btnThree.setRadius(convertDpToPx(6));
        btnThree.setCardElevation(0);
        btnThree.addView(linearLayout);

        btnThree.setVisibility(GONE);
        addView(btnThree);

        // Add to list
        tvList.add(tvThree);
        ivList.add(ivThree);
        buttonList.add(btnThree);
    }

    private void initFourthMenu(Context context) {
        // Menu title
        tvFour = new TextView(context);
        LinearLayout.LayoutParams tvFourLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvFour.setTextSize(DEFAULT_TEXT_SIZE);
        tvFour.setText("Notification");
        tvFour.setLayoutParams(tvFourLayout);

        // Menu icon
        ivFour = new ImageView(context);
        LinearLayout.LayoutParams ivFourLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivFour.setImageResource(R.drawable.ic_notification);
        ivFour.setLayoutParams(ivFourLayout);

        // Linear layout for holding title and icon vertically
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams llLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(convertDpToPx(5), convertDpToPx(5), convertDpToPx(5), convertDpToPx(5));
        linearLayout.setLayoutParams(llLayout);

        linearLayout.addView(ivFour);
        linearLayout.addView(tvFour);

        // Card for holding the linear layout
        btnFour = new MaterialCardView(context);
        btnFour.setId(View.generateViewId());
        btnFour.setClickable(true);
        btnFour.setFocusable(true);
        btnFour.setRadius(convertDpToPx(6));
        btnFour.setCardElevation(0);
        btnFour.addView(linearLayout);

        btnFour.setVisibility(GONE);
        addView(btnFour);

        // Add to list
        tvList.add(tvFour);
        ivList.add(ivFour);
        buttonList.add(btnFour);
    }

    private void initFifthMenu(Context context) {
        // Menu title
        tvFive = new TextView(context);
        LinearLayout.LayoutParams tvFiveLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvFive.setTextSize(DEFAULT_TEXT_SIZE);
        tvFive.setText("Setting");
        tvFive.setLayoutParams(tvFiveLayout);

        // Menu icon
        ivFive = new ImageView(context);
        LinearLayout.LayoutParams ivFiveLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivFive.setImageResource(R.drawable.ic_setting);
        ivFive.setLayoutParams(ivFiveLayout);

        // Linear layout for holding title and icon vertically
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams llLayout = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(convertDpToPx(5), convertDpToPx(5), convertDpToPx(5), convertDpToPx(5));
        linearLayout.setLayoutParams(llLayout);

        linearLayout.addView(ivFive);
        linearLayout.addView(tvFive);

        // Card for holding the linear layout
        btnFive = new MaterialCardView(context);
        btnFive.setId(View.generateViewId());
        btnFive.setClickable(true);
        btnFive.setFocusable(true);
        btnFive.setRadius(convertDpToPx(6));
        btnFive.setCardElevation(0);
        btnFive.addView(linearLayout);

        btnFive.setVisibility(GONE);
        addView(btnFive);

        // Add to list
        tvList.add(tvFive);
        ivList.add(ivFive);
        buttonList.add(btnFive);
    }

    private void initConstraintSet() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);

        // Set constraint layout btnOne
        constraintSet.constrainWidth(btnOne.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(btnOne.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(btnOne.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(btnOne.getId(), ConstraintSet.END, btnTwo.getId(), ConstraintSet.START);
        constraintSet.connect(btnOne.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(btnOne.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.setHorizontalChainStyle(btnOne.getId(), ConstraintSet.CHAIN_SPREAD);

        // Set constraint layout btnTwo
        constraintSet.constrainWidth(btnTwo.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(btnTwo.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(btnTwo.getId(), ConstraintSet.START, btnOne.getId(), ConstraintSet.END);
        constraintSet.connect(btnTwo.getId(), ConstraintSet.END, btnThree.getId(), ConstraintSet.START);
        constraintSet.connect(btnTwo.getId(), ConstraintSet.TOP, btnOne.getId(), ConstraintSet.TOP);
        constraintSet.connect(btnTwo.getId(), ConstraintSet.BOTTOM, btnOne.getId(), ConstraintSet.BOTTOM);

        // Set constraint layout btnThree
        constraintSet.constrainWidth(btnThree.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(btnThree.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(btnThree.getId(), ConstraintSet.START, btnTwo.getId(), ConstraintSet.END);
        constraintSet.connect(btnThree.getId(), ConstraintSet.END, btnFour.getId(), ConstraintSet.START);
        constraintSet.connect(btnThree.getId(), ConstraintSet.TOP, btnTwo.getId(), ConstraintSet.TOP);
        constraintSet.connect(btnThree.getId(), ConstraintSet.BOTTOM, btnTwo.getId(), ConstraintSet.BOTTOM);

        // Set constraint layout btnFour
        constraintSet.constrainWidth(btnFour.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(btnFour.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(btnFour.getId(), ConstraintSet.START, btnThree.getId(), ConstraintSet.END);
        constraintSet.connect(btnFour.getId(), ConstraintSet.END, btnFive.getId(), ConstraintSet.START);
        constraintSet.connect(btnFour.getId(), ConstraintSet.TOP, btnThree.getId(), ConstraintSet.TOP);
        constraintSet.connect(btnFour.getId(), ConstraintSet.BOTTOM, btnThree.getId(), ConstraintSet.BOTTOM);

        // Set constraint layout btnFive
        constraintSet.constrainWidth(btnFive.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(btnFive.getId(), ConstraintSet.WRAP_CONTENT);
        constraintSet.connect(btnFive.getId(), ConstraintSet.START, btnFour.getId(), ConstraintSet.END);
        constraintSet.connect(btnFive.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        constraintSet.connect(btnFive.getId(), ConstraintSet.TOP, btnFour.getId(), ConstraintSet.TOP);
        constraintSet.connect(btnFive.getId(), ConstraintSet.BOTTOM, btnFour.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(this);
    }

    private void initColor() {
        selectedColor = Color.parseColor(DEFAULT_SELECTED_COLOR);
        unselectedColor = Color.parseColor(DEFAULT_UNSELECTED_COLOR);
    }

    private void highlightSelectedMenu() {
        tvOne.setTextColor(unselectedColor);
        ivOne.setImageTintList(ColorStateList.valueOf(unselectedColor));
        tvTwo.setTextColor(unselectedColor);
        ivTwo.setImageTintList(ColorStateList.valueOf(unselectedColor));
        tvThree.setTextColor(unselectedColor);
        ivThree.setImageTintList(ColorStateList.valueOf(unselectedColor));
        tvFour.setTextColor(unselectedColor);
        ivFour.setImageTintList(ColorStateList.valueOf(unselectedColor));
        tvFive.setTextColor(unselectedColor);
        ivFive.setImageTintList(ColorStateList.valueOf(unselectedColor));

        switch (selectedMenu) {
            case MENU_ONE:
                tvOne.setTextColor(selectedColor);
                ivOne.setImageTintList(ColorStateList.valueOf(selectedColor));
                break;
            case MENU_TWO:
                tvTwo.setTextColor(selectedColor);
                ivTwo.setImageTintList(ColorStateList.valueOf(selectedColor));
                break;
            case MENU_THREE:
                tvThree.setTextColor(selectedColor);
                ivThree.setImageTintList(ColorStateList.valueOf(selectedColor));
                break;
            case MENU_FOUR:
                tvFour.setTextColor(selectedColor);
                ivFour.setImageTintList(ColorStateList.valueOf(selectedColor));
                break;
            case MENU_FIVE:
                tvFive.setTextColor(selectedColor);
                ivFive.setImageTintList(ColorStateList.valueOf(selectedColor));
                break;
            default:
                break;
        }
    }

    public void setMenuItem(List<NavBarMenuModel> navBarMenuList) {
        this.navBarMenuList = navBarMenuList;
        for (int i = 0; i < navBarMenuList.size(); i++) {
            buttonList.get(i).setVisibility(VISIBLE);
            ivList.get(i).setImageResource(navBarMenuList.get(i).getMenuIcon());
            tvList.get(i).setText(navBarMenuList.get(i).getMenuName());
        }
    }

    public void actionListener(@NonNull NavBarAction action) {
        for (int i = 0; i < navBarMenuList.size(); i++) {
            int finalI = i;
            buttonList.get(i).setOnClickListener(view -> {
                switch (finalI) {
                    case (0):
                        selectedMenu = MENU_ONE;
                        break;
                    case (1):
                        selectedMenu = MENU_TWO;
                        break;
                    case (2):
                        selectedMenu = MENU_THREE;
                        break;
                    case (3):
                        selectedMenu = MENU_FOUR;
                        break;
                    case (4):
                        selectedMenu = MENU_FIVE;
                        break;
                    default:
                        break;
                }
                highlightSelectedMenu();

                action.clickListener(view, selectedMenu);
            });
        }
    }

    private int convertDpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5F);
    }

    public static class NavBarMenuModel {
        private int menuIcon;
        private String menuName;

        public NavBarMenuModel(int menuIcon, String menuName) {
            this.menuIcon = menuIcon;
            this.menuName = menuName;
        }

        public int getMenuIcon() {
            return menuIcon;
        }

        public String getMenuName() {
            return menuName;
        }
    }

    public interface NavBarAction {
        void clickListener(View view, int buttonNumber);
    }
}