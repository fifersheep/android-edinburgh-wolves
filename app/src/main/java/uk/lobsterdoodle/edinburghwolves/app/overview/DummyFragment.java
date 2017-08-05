package uk.lobsterdoodle.edinburghwolves.app.overview;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.lobsterdoodle.edinburghwolves.app.R;
import uk.lobsterdoodle.edinburghwolves.app.view.ScoreboardView;

public class DummyFragment extends Fragment {

    ScoreboardView scoreboard;

    private OnFragmentInteractionListener mListener;

    public DummyFragment() {
        // Required empty public constructor
    }

    public static DummyFragment newInstance() {
        return new DummyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_wolves_home, container, false);
        scoreboard = (ScoreboardView) inflate.findViewById(R.id.scoreboard);
        scoreboard.setTeams("Wolves", "Trojans").updateScores("6", "3");
        return inflate;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Learn about Fragment interaction
        void onFragmentInteraction(Uri uri);
    }
}
