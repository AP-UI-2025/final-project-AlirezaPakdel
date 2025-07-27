package org.example.plantvszombies.Model;

import javafx.animation.Timeline;

import java.util.ArrayList;
import java.util.List;

public class TimelineManager {
    private static final TimelineManager instance = new TimelineManager();
    private static final List<Timeline> timelines = new ArrayList<>();

    private TimelineManager() {}
    public static TimelineManager getInstance() {
        return instance;
    }

    public void add(Timeline timeline) {
        timelines.add(timeline);
    }

    public void stopAll() {
        for (Timeline t : timelines) {
            t.stop();
        }
    }

    public void clear() {
        stopAll();
        timelines.clear();
    }

    public List<Timeline> getTimelines() {
        return timelines;
    }
}
