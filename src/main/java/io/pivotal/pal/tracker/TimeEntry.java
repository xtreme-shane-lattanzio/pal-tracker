package io.pivotal.pal.tracker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by pivotal on 2017-07-11.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private String date;
    private int hours;

    public TimeEntry(long projectId, long userId, String date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

}
