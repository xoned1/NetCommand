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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VolumeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VolumeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VolumeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View.OnClickListener volumeUpListener = (view -> {
        volumeUp(getIP());
    }
    );
    private View.OnClickListener volumeMuteListener = (view -> volumeMute(getIP()));
    private View.OnClickListener volumeDownListener = (view -> volumeDown(getIP()));
    private ClientConnection clientConnection;

    public VolumeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.volume_fragment, container, false);
        this.clientConnection = ClientProvider.get(getIP());

        FrameLayout flbuttonVolUp = (FrameLayout)view.findViewById(R.id.flBtnVolUp);
        flbuttonVolUp.setOnClickListener(volumeUpListener);
        Button buttonVolUp = (Button)view.findViewById(R.id.btnVolumeUp);
        buttonVolUp.setOnClickListener(volumeUpListener);


        Button btnMute = (Button)view.findViewById(R.id.btnMute);
        btnMute.setOnClickListener(volumeMuteListener);
        FrameLayout flBtnMute = (FrameLayout)view.findViewById(R.id.flBtnVolMute);
        flBtnMute.setOnClickListener(volumeMuteListener);

        Button buttonVolDown = (Button)view.findViewById(R.id.btnVolumeDown);
        buttonVolDown.setOnClickListener(volumeDownListener);
        FrameLayout flBtnVolDown = (FrameLayout)view.findViewById(R.id.flBtnVolDown);
        flBtnVolDown.setOnClickListener(volumeDownListener);

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private String getIP() {
        return getActivity().getIntent().getStringExtra("ip");
    }

    private void volumeUp(String ip) {
        this.clientConnection.execute("VOLUME_UP");
    }

    private void volumeMute(String ip) {
        this.clientConnection.execute("VOLUME_MUTE");
    }

    private void volumeDown(String ip) {
        this.clientConnection.execute("VOLUME_DOWN");
    }
}
