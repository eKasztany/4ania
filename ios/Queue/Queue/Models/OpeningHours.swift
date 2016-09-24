//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation

struct OpeningHours {
    let mon: String
    let tue: String
    let wen: String
    let thr: String
    let fri: String
}

extension OpeningHours {
    init?(dictionary: JSONDictionary) {
        guard let mon = dictionary["mon"] as? String,
            let tue = dictionary["tue"] as? String,
            let wen = dictionary["wen"] as? String,
            let thr = dictionary["thr"] as? String,
            let fri = dictionary["fri"] as? String else { return nil }
        self.mon = mon
        self.tue = tue
        self.wen = wen
        self.thr = thr
        self.fri = fri
    }
}
