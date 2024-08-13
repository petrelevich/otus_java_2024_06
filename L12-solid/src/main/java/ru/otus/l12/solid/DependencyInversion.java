package ru.otus.l12.solid;

/**
 * @author sergey created on 09.09.19.
 */
@SuppressWarnings({"java:S1068", "java:S1481", "java:S1144", "java:S1854"})
public class DependencyInversion {

    interface SteeringWheel {}

    interface Engine {}

    /*неправильно*/
    class CarInvalid {
        private SteeringWheel steeringWheel;
        private Engine engine;

        public CarInvalid() {
            this.steeringWheel = new GenericWheel();
            this.engine = new GasEngine();
        }
    }

    class CarDiesel {
        private SteeringWheel steeringWheel;
        private Engine engine;

        public CarDiesel() {
            this.steeringWheel = new GenericWheel();
            this.engine = new DieselEngine();
        }
    }

    /*Правильное применение принципа*/
    class Car {
        private SteeringWheel steeringWheel;
        private Engine engine;

        public Car(SteeringWheel steeringWheel, Engine engine) {
            this.steeringWheel = steeringWheel;
            this.engine = engine;
        }

        public void move() {
            //
        }
    }

    private void smMthd() {

        var car = new Car(new GenericWheel(), new DieselEngine());
        var car2 = new Car(new GenericWheel(), new GasEngine());
    }

    class GasEngine implements Engine {}

    class DieselEngine implements Engine {}

    class GenericWheel implements SteeringWheel {}
}
