import kotcity.data.*
import org.junit.jupiter.api.Test

class PowerLayerTest {
    @Test
    fun testPowerSpread() {
        val map = CityMap(512, 512)
        // set all tiles to ground...
        val xRange = 0 .. map.width
        val yRange = 0 .. map.height
        xRange.map { x ->
            yRange.map { y ->
                map.groundLayer[BlockCoordinate(x, y)] = MapTile(TileType.GROUND, 0.1)
            }
        }

        // now let's drop a coal power plant...
        val powerPlant1 = PowerPlant("coal")
        val powerPlant2 = PowerPlant("coal")

        map.build(powerPlant1, BlockCoordinate(0, 0))
        map.build(powerPlant2, BlockCoordinate(0, 20))

        // ok... that power plant will end at 3,0
        val roadStart = BlockCoordinate(4,0)
        val roadEnd = BlockCoordinate(4, 40)

        map.buildRoad(roadStart, roadEnd)

        PowerCoverageUpdater.update(map)

    }
}

