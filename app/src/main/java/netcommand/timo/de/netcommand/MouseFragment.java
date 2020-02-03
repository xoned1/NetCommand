package netcommand.timo.de.netcommand;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


public class MouseFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View.OnClickListener leftClickListener = (view) -> leftClick(getIp());
    private View.OnClickListener rightClickListener = (view) -> rightClick(getIp());
    private ClientConnection clientConnection;

    public MouseFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_mouse, container, false);
        this.clientConnection = ClientProvider.get(getIp());

        FrameLayout frBtnLeftClick = (FrameLayout)view.findViewById(R.id.frBtnLeftClick);
        frBtnLeftClick.setOnClickListener(leftClickListener);
        Button btnLeftClick = (Button)view.findViewById(R.id.btnLeftClick);
        btnLeftClick.setOnClickListener(leftClickListener);

        FrameLayout frBtnRightClick = (FrameLayout)view.findViewById(R.id.frBtnRightClick);
        frBtnRightClick.setOnClickListener(rightClickListener);
        Button btnRightClick = (Button)view.findViewById(R.id.btnRightClick);
        btnRightClick.setOnClickListener(rightClickListener);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public String getIp() {
        return getActivity().getIntent().getStringExtra("ip");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void leftClick(String ip) {
        this.clientConnection.execute("CLICK_LEFT");
    }

    private void rightClick(String ip) {
       this.clientConnection.execute("CLICK_RIGHT");
    }
}
