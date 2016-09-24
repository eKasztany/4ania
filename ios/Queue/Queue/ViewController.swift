//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        FirebaseClient().load(resource: Deparment.all) { result in
            print(result)
        }
    }
}
