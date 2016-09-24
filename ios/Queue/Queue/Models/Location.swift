//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation

struct Location {
    let lat: Double
    let lon: Double
}

extension Location {
    init?(dictionary: JSONDictionary) {
        guard let lat = dictionary["lat"] as? Double,
            let lon = dictionary["lon"] as? Double else { return nil }
        self.lat = lat
        self.lon = lon
    }
}
