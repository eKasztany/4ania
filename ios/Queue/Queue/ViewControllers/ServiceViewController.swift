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

    @IBOutlet weak var collectionView: UICollectionView!
    var departmentId: String?
    var state: State = .loading {
        didSet {
            switch state {
            case .loaded(_):
                collectionView.reloadData()
            default:
                break
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        collectionView.dataSource = self
        collectionView.delegate = self

        FirebaseClient().load(resource: Service.all) { result in
            guard let result = result, let service = result.filter({ $0.uid == self.departmentId }).first else {
                self.state = .error
                return
            }
            self.state = .loaded(service.serviceGroups)
        }
    }
}

extension ServiceViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = (collectionView.frame.width - 30) * 0.5
        return CGSize(width: width, height: width)
    }
}

extension ServiceViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch state {
        case .loaded(let services):
            return services.count
        default:
            return 0
        }
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        switch state {
        case .loaded(let services):
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: R.reuseIdentifier.serviceCell, for: indexPath)!
            cell.configure(service: services[indexPath.row])
            return cell
        default:
            return UICollectionViewCell()
        }
    }
}
