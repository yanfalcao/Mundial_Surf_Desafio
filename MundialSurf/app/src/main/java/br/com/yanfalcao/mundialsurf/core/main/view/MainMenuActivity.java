package br.com.yanfalcao.mundialsurf.core.main.view;

import android.animation.ArgbEvaluator;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends AppCompatActivity {
    public static String IMAGE = "IMAGE";
    public static String TITLE = "TITLE";

    @BindView(R.id.viewPager) ViewPager viewPager;
    PagerAdapter adapter;
    List<Map<String, Object>> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

        modelInicialize();
        colorsInicialize();

        adapter = new MainMenuAdapter(models, this);

        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i < (adapter.getCount() - 1) && i < (colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    v,
                                    colors[i],
                                    colors[i+1]));
                }else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void modelInicialize(){
        Map<String, Object> item = new HashMap<>();
        models = new ArrayList<>();

        item.put(IMAGE, R.drawable.img_woman_surfer);
        item.put(TITLE, getString(R.string.surfer_menu));
        models.add(item);

        item = new HashMap<>();
        item.put(IMAGE, R.drawable.img_hit);
        item.put(TITLE, getString(R.string.hit_menu));
        models.add(item);
    }

    private void colorsInicialize(){
        Integer[] colors_helper = {
                getResources().getColor(R.color.surfer),
                getResources().getColor(R.color.hit)
        };

        colors = colors_helper;
    }
}
