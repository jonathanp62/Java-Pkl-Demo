package net.jmp.demo.pkl;

/*
 * (#)Main.java 0.1.0   02/17/2024
 *
 * @author    Jonathan Parker
 * @version   0.1.0
 * @since     0.1.0
 *
 * MIT License
 *
 * Copyright (c) 2024 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import org.pkl.config.java.Config;
import org.pkl.config.java.ConfigEvaluator;
import org.pkl.config.java.JavaType;

import org.pkl.core.ModuleSource;

import org.slf4j.LoggerFactory;

import org.slf4j.ext.XLogger;

public final class Main {
    private final XLogger logger = new XLogger(LoggerFactory.getLogger(this.getClass().getName()));

    private Main() {
        super();
    }

    private void run() {
        this.logger.entry();

        this.birds();
        this.application();

        this.logger.exit();
    }

    private void birds() {
        this.logger.entry();

        Config config;

        // To learn more about the `ConfigEvaluator` API, see the `config-java` example.

        try (final var evaluator = ConfigEvaluator.preconfigured()) {
            // "Module path" here represents Java's classpath. Since we put `my-birds.pkl`
            // inside the resources directory, it is available in the classpath.

            config = evaluator.evaluate(ModuleSource.modulePath("/my-birds.pkl"));
        }

        // Convert entire config to an instance of the generated config class.
        // This is the most convenient and most type-safe approach.

        final var birds = config.as(Birds.class);

        this.logger.info("There are {} birds configured", birds.birds.size());

        if (this.logger.isInfoEnabled())
            this.logger.info(birds.birds.get("Parrot").toString());

        // Only convert the `birds` mapping

        final var birdsMap = config.get("birds").as(JavaType.mapOf(String.class, Birds.Bird.class));

        if (this.logger.isInfoEnabled())
            this.logger.info(birdsMap.get("Penguin").toString());

        // Only convert the bird named "Peacock"

        final var peacock = config.get("birds").get("Peacock").as(Birds.Bird.class);

        if (this.logger.isInfoEnabled())
            this.logger.info(peacock.toString());

        this.logger.exit();
    }

    private void application() {
        this.logger.entry();

        Config config;

        // To learn more about the `ConfigEvaluator` API, see the `config-java` example.

        try (final var evaluator = ConfigEvaluator.preconfigured()) {
            config = evaluator.evaluate(ModuleSource.path("config/my-application.pkl"));
        }

        final var application = config.as(Application.class);

        if (this.logger.isInfoEnabled())
            this.logger.info(application.toString());

        this.logger.exit();
    }

    public static void main(final String[] args) {
        new Main().run();
    }
}