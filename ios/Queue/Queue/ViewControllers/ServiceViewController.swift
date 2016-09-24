//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit

class ServiceViewController: UIViewController {

    enum State {
        case loading
        case loaded([ServiceGroup])
        case empty
        case error
    }

    @IBOutlet weak var tableView: UITableView!
    var departmentId: String?
    var state: State = .loading {
        didSet {
            switch state {
            case .loaded(_):
                tableView.reloadData()
            default:
                break
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.dataSource = self

        FirebaseClient().load(resource: Service.all) { result in
            guard let result = result, let service = result.filter({ $0.uid == self.departmentId }).first else {
                self.state = .error
                return
            }
            self.state = .loaded(service.serviceGroups)
        }
    }
}

extension ServiceViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        switch state {
        case .loaded(let services):
            let cell = tableView.dequeueReusableCell(withIdentifier: "serviceCell", for: indexPath) as UITableViewCell
            cell.textLabel?.text = services[indexPath.row].name
            return cell
        default:
            break
        }
        return UITableViewCell()
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch state {
        case .loaded(let services):
            return services.count
        default:
            return 1
        }
    }
}
