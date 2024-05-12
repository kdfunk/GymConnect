package com.example.connectgym;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.List;

public class GymDataUpdateWorker extends Worker {

    public GymDataUpdateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            // Perform scraping
            List<Gym> gyms = WebScraper.scrapeGymData("http://example.com/gyms");
            // Update database
            new DbHelper(getApplicationContext()).updateGymData(gyms);
            return Result.success();
        } catch (Exception e) {
            return Result.failure();
        }
    }
}
