//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation

struct Address {
    let city: String
    let code: String
    let street: String
}

extension Address {
    init?(dictionary: JSONDictionary) {
        guard let city = dictionary["city"] as? String,
            let code = dictionary["code"] as? String,
            let street = dictionary["street"] as? String else { return nil }
        self.city = city
        self.code = code
        self.street = street
    }
}
