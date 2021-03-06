package com.id.yunus.bottomnav;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Fragment class for each nav menu item
 */
public class MenuFragment extends Fragment {
    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    private String strText;
    private int intColor;

    private View mContent;
    private TextView mTextView;

    public static Fragment newInstance(String text, int color) {
        Fragment frag = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // retrieve text and color from bundle or savedInstanceState
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            strText = args.getString(ARG_TEXT);
            intColor = args.getInt(ARG_COLOR);
        } else {
            strText = savedInstanceState.getString(ARG_TEXT);
            intColor = savedInstanceState.getInt(ARG_COLOR);
        }

        // initialize views
        mContent = view.findViewById(R.id.fragment_content);
        mTextView = (TextView) view.findViewById(R.id.text);

        // set text and background color
        mTextView.setText(strText);
        mContent.setBackgroundColor(intColor);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, strText);
        outState.putInt(ARG_COLOR, intColor);
        super.onSaveInstanceState(outState);
    }
}
