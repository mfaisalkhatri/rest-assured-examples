package io.github.mfaisalkhatri;

import lombok.Getter;
import lombok.Setter;

/**
 * Created By Faisal Khatri on 19-11-2021
 */
@Getter
@Setter
public class PostData {

    private final String name;
    private final String job;

    /**
     * Created By Faisal Khatri on 19-11-2021
     *
     * @param name
     * @param job
     */
    public PostData (final String name, final String job) {
        this.name = name;
        this.job = job;

    }
}