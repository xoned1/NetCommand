package netcommand.timo.de.netcommand;

import android.net.Uri;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CommandActivity
        extends
        AppCompatActivity
        implements
        VolumeFragment.OnFragmentInteractionListener,
        MiscFragment.OnFragmentInteractionListener,
        MouseFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        getSupportActionBar().setTitle("Volume");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framelayout, new VolumeFragment());
        transaction.commit();

        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(item -> {

            getSupportActionBar().setTitle(item.toString());
            switch (item.toString()) {

                case "Volume":
                    FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.framelayout, new VolumeFragment());
                    trans.commit();
                    break;
                case "Misc":
                    trans = getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.framelayout, new MiscFragment());
                    trans.commit();
                    break;
                case "Mouse":
                    trans = getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.framelayout, new MouseFragment());
                    trans.commit();
                    break;
            }
            return true;

        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
