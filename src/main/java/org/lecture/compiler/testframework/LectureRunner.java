package org.lecture.compiler.testframework;

/*
 * Copyright (c) 2015 Rene Richter.
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */


import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.lecture.compiler.api.ExerciseContext;

/**
 * A custom JUnit runner to alter instantiation of unit tests.
 * @author Rene Richter
 */
public class LectureRunner extends BlockJUnit4ClassRunner {

  private ExerciseContext context;

  /**
   * Creates a BlockJUnit4ClassRunner to run {@code klass}
   *
   * @param klass   The class containing test-cases.
   * @param context The exercise context that will be injected into the test case instance.
   * @throws InitializationError if the test class is malformed.
   */
  public LectureRunner(Class<?> klass,
                       ExerciseContext context) throws InitializationError {

    super(klass);
    this.context = context;
  }

  @Override
  protected Object createTest() throws Exception {

    Object testInstance = super.createTest();
    if (testInstance instanceof AbstractTest) {
      AbstractTest test = (AbstractTest) testInstance;
      test.setExerciseContainer(context);
    }
    return testInstance;
  }
}
