//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    enum State {
        case loading
        case loaded([Deparment])
        case empty
        case error
    }

    var state: State = .loading

    override func viewDidLoad() {
        super.viewDidLoad()
        FirebaseClient().load(resource: Deparment.all) { result in
            if let result = result {
                self.state = .loaded(result)
            } else {
                self.state = .error
            }
            print(result)
        }

        RemoteNotificationsManager.register { (result) in
            print(result)
        }
    }
}
