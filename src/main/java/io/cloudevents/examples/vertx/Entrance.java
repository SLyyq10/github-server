package io.cloudevents.examples.vertx;

import com.linkall.vance.core.VanceApplication;

public class Entrance {
    public static void main(String[] args) {
        VanceApplication.run(GitHubHttpSource.class);
    }
}
