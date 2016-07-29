package uk.lobsterdoodle.edinburghwolves.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import uk.lobsterdoodle.edinburghwolves.app.di.DaggerWolvesComponent;
import uk.lobsterdoodle.edinburghwolves.app.di.WolvesComponent;
import uk.lobsterdoodle.edinburghwolves.app.di.WolvesModule;
import uk.lobsterdoodle.edinburghwolves.app.dummy.DummyContent;
import uk.lobsterdoodle.edinburghwolves.app.dummy.DummyContent.DummyItem;
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter;
import uk.lobsterdoodle.edinburghwolves.core.view.RosterListFragmentView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RosterListFragment extends Fragment implements RosterListFragmentView {
    private OnListFragmentInteractionListener mListener;
    private RosterListFragmentPresenter presenter;

    public RosterListFragment() {
    }

    @SuppressWarnings("unused")
    public static RosterListFragment newInstance() {
        return new RosterListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WolvesComponent component = DaggerWolvesComponent.builder().wolvesModule(new WolvesModule()).build();
        presenter = component.provideRosterListFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roster_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new RosterListItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }

        presenter.onCreateView(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void makeToast() {
        Toast.makeText(getActivity(), "Dependency Injection 2.0!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
